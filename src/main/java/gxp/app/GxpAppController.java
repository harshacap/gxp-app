package gxp.app;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import gxp.app.entity.GxpEntity;
import gxp.app.service.GxpService;
import gxp.app.util.JsonParser;

@RestController
public class GxpAppController {

	@Value("${nge.disney.gxp.host}")
	private String gxpServiceHost;

	@Value("${nge.disney.gxp.context.root}")
	private String gxpServiceContextRoot;

	@Value("${nge.disney.gxp.endpoint}")
	private String gxpServiceEndpoint;

	@Autowired
	GxpService gxpService;

	@RequestMapping(value = "/storefastpasspayload", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<String> processGxpId(@RequestBody String payLoad) {
		ResponseEntity<String> resp = null;
		try {
			GxpEntity ge = JsonParser.parseJsonStringToPojo(payLoad);
			boolean flag = gxpService.getGxpPayLoadDetails(ge.getIdentifierValue());
			if (!flag) {
				gxpService.persistGxpPayload(ge,
						"{" + payLoad.replaceAll("\"xpasses\":", "").replaceAll("[\\[{}\\]]", "") + "}");
			} else {
				gxpService.updateGxpPayload(ge,
						"{" + payLoad.replaceAll("\"xpasses\":", "").replaceAll("[\\[{}\\]]", "") + "}");
			}
			invokeGxpServiceApp(ge.getSwid(), "gxp-link-id", ge.getGxpLinkId());
			resp = new ResponseEntity<String>("Published Successfully! ", HttpStatus.OK);
		} catch (Exception e) {
			resp = new ResponseEntity<String>("Failed To Publish", HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		return resp;
	}

	private void invokeGxpServiceApp(String swid, String gxpLinkType, String gxpLinkIdValue) {

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.TEXT_PLAIN);
		headers.set("from", "GXP");
		HttpEntity<String> entity = new HttpEntity<String>(swid, headers);
		String serviceUri = gxpServiceHost + "/" + gxpServiceContextRoot + gxpServiceEndpoint + "?swid=" + swid
				+ "&guestIdType=" + gxpLinkType + "&guestIdValue=" + gxpLinkIdValue;

		RestTemplate restTemplate = new RestTemplate();
		restTemplate.postForLocation(serviceUri, entity);

	}

	@RequestMapping(value = "/{gxpLinkId}", method = RequestMethod.GET)
	public List gxpPayLoadBygxpLinkId(@PathVariable String gxpLinkId) {

		List gxpPayLoadlist = gxpService.getGxpDetails(gxpLinkId);
		System.out.println("gsxpdata" + gxpPayLoadlist);
		return gxpPayLoadlist;
	}

}
