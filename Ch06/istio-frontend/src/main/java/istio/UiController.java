/**
 *
 */
package istio;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author avinashsingh
 *
 */
@Controller
public class UiController {

    private static final String WEBSERVICE_HOST = System.getenv("WEBSERVICE_SERVICE_HOST");
    private static final String WEBSERVICE_PORT = System.getenv("WEBSERVICE_SERVICE_PORT");

    @GetMapping("/")
    public ModelAndView page () throws MalformedURLException {
        // get python service link
        String webServiceUrl = "http://webservice/";

        ModelAndView mav = new ModelAndView("home");
        
        // fetch data from python service
        URL url = new URL(webServiceUrl);
        try {
            String response = IOUtils.toString(url.openStream(), "UTF-8");
            mav.addObject("serviceresponse", response);
        } catch (IOException e) {
            e.printStackTrace();
            mav.addObject("serviceresponse", "ERROR RECEIVED");
            mav.setStatus(org.springframework.http.HttpStatus.SERVICE_UNAVAILABLE);
        }

        return mav;
    }
}
