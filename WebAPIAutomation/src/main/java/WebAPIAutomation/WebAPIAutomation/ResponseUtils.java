package WebAPIAutomation.WebAPIAutomation;

import java.util.Arrays;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.CloseableHttpResponse;

public class ResponseUtils {

	
	/**
	 * 
	 * @param response: response object
	 * @param headerOfInterest: Header for which the value is to be fetched. 
	 * @return
	 */
	
	public static String getHeader(CloseableHttpResponse response, String headerOfInterest) {

		// Get all headers
	
		Header[] headers = response.getAllHeaders();
        String returnHeader = "";
		
		// Loop over the headers list
		
		for(Header header: headers ) {
			
			if(headerOfInterest.equalsIgnoreCase(header.getName())) {
				returnHeader = header.getValue();
			}
			
		}
		
		// If Header not found, throw an exception
		
		if(returnHeader.isEmpty()) {
			throw new RuntimeException("Didn't find the header: "+ headerOfInterest);
		}
		
		// Return the header
		
		return returnHeader;
	}

	public static String getHeaderJava8Way(CloseableHttpResponse response, String headerOfInterest) {
		
		List<Header> httpHeaders = Arrays.asList(response.getAllHeaders());
        
		Header matchedHeader = httpHeaders.stream()
								.filter(header -> headerOfInterest.equalsIgnoreCase(header.getName()))
								.findFirst().orElseThrow(() -> new RuntimeException("Didn't find the header: "+headerOfInterest));
		
		return matchedHeader.getValue();
	}

	public static Boolean checkIfHeaderIsPresent(CloseableHttpResponse response, String headerOfInterest) {
		
		List<Header> httpHeaders = Arrays.asList(response.getAllHeaders());
		
		return httpHeaders.stream()
				.anyMatch(header -> header.getName().equalsIgnoreCase(headerOfInterest));
		
	}
}
