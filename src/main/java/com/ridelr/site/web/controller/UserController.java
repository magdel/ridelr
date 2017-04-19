package com.ridelr.site.web.controller;

import com.ridelr.site.web.auth.AuthenticationFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {

    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AuthenticationFacade authenticationFacade;

    /*  @Autowired
      private OUserPersistenceService userPersistenceService;
  */
    /*@Autowired
    private CaptchaService captchaService;
*/
  /*  @Autowired
    private MailSender mailSender;
*/
    public void autoLogin(HttpServletRequest request, String userName, String password) {

        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(userName, password);

        // Authenticate the user
        Authentication authentication = authenticationManager.authenticate(authRequest);
        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(authentication);

        // Create a new session and add the security context.
        HttpSession session = request.getSession(true);
        session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);
    }


   /* @RequestMapping(value = "/info", method = RequestMethod.GET)
    public String showCurrentUserInfo(Model model) {
        String userId = authenticationFacade.getUserId();
        LOG.debug("OCU to current user " + userId + " view");
        if (userId == null || userId.isEmpty()) {
            return "redirect:/";
        }

        return fillUserProfile(userId, model);
    }
*/


   /* private String fillUserProfile(@PathVariable("userId") String userId, Model model) {
        UserDetailsFindEvent userDetails = userPersistenceService.findOUserDetails(new UserDetailsRequestEvent(userId));
        if (!userDetails.isEntityFound()) {
            //throw new RuntimeException("sdsdsds");
            throw new PageNotFoundException("User " + userId);
        }
        PublicUser publicUser = PublicUser.fromUserDetails(userDetails.getOUserDetails());
        model.addAttribute("publicUser", publicUser);
        Authentication auth = authenticationFacade.getAuthentication();
        if (auth.isAuthenticated()) {
            final String authUserId = authenticationFacade.getUserId();
            if (userId.equals(authUserId)) {
                PrivateUser privateUser = PrivateUser.fromUserDetails(userDetails.getOUserDetails());
                model.addAttribute("privateUser", privateUser);
            } else {
                PrivateUser privateUser = PrivateUser.empty();
                model.addAttribute("privateUser", privateUser);
            }
        }

        return "user/userinfo";
    }*/

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String getRegister(Model model) {
        LOG.debug("OCU to register view");
        return "user/register";
    }




   /* @RequestMapping(value = "/{deviceId}/edit", method = RequestMethod.GET)
    public String showEventDetailsForEdit(@PathVariable("deviceId") String deviceId, Model model) {
        LOG.debug("OCU to device " + deviceId + " edit");
        model.addAttribute("device", Device.fromDeviceDetails(
                deviceService.requestDeviceDetails(
                        new RequestDeviceDetailsEvent(deviceId, authenticationFacade.getUser().getId())).getDeviceDetails()));
        return "/devices/deviceedit";
    }

    @RequestMapping(value = "/{deviceId}/delete", method = RequestMethod.POST)
    public String deleteDevice(@PathVariable("deviceId") String deviceId,
                               RedirectAttributes redirectAttrs) {
        LOG.debug("OCU to device " + deviceId + " delete");
        deviceService.deleteDevice(new RequestDeviceDetailsEvent(deviceId, authenticationFacade.getUser().getId()));
        redirectAttrs.addFlashAttribute("message",
                "Device has been deleted!");
        return "redirect:/devices/";
    }

    @RequestMapping(value = "/ucreate", method = RequestMethod.POST)
    public String doCreate(Device device,
                           BindingResult result,
                           RedirectAttributes redirectAttrs) {
        Authentication auth = authenticationFacade.getAuthentication();
        LOG.info("Come new " + device + " from " + auth.getName());
        if (result.hasErrors()) {
            LOG.warn("Some error " + device + "," + result);
            // errors in the form
            // show the checkout form again
            return "/devices/deviceedit";
        }

        LOG.debug("No errors, continue with processing for:" + device.getName());

        DeviceDetails deviceDetails = device.createDeviceDetails(authenticationFacade.getUser().getId());

        DeviceDetailsEvent deviceDetailsEvent = deviceService
                .createDevice(new CreateDeviceEvent(deviceDetails));

        LOG.debug("created: " + deviceDetailsEvent);
        redirectAttrs.addFlashAttribute("message",
                "Device has been created!");

        return "redirect:/devices/" + deviceDetailsEvent.getKey();
    }


    @RequestMapping(value = "/{deviceId}/connect", method = RequestMethod.POST)
    public String doConnect(@PathVariable("deviceId") String deviceId, RedirectAttributes redirectAttrs) {
        Authentication auth = authenticationFacade.getAuthentication();
        LOG.info("Connect " + deviceId + " from " + auth.getName());

        ConnectToken connectToken = new ConnectToken();
        ConnTokenDetailsEvent connTokenDetailsEvent = connectDeviceService.createConnectDevice(
                new CreateConnTokenEvent(connectToken.createDeviceDetails(deviceId, authenticationFacade.getUserId())));

        LOG.debug("created: " + connTokenDetailsEvent);
        redirectAttrs.addFlashAttribute("message",
                "Connect has been created!");

        return "redirect:/devices/" + deviceId;
    }

    @RequestMapping(value = "/{deviceTokenId}/disconnect", method = RequestMethod.POST)
    public String doDisConnect(@PathVariable("deviceTokenId") String deviceTokenId, RedirectAttributes redirectAttrs) {
        Authentication auth = authenticationFacade.getAuthentication();
        LOG.info("DisConnect " + deviceTokenId + " from " + auth.getName());

        final DeviceTokenDetailsEvent deviceTokenDetailsEvent = deviceTokenService.deleteDeviceToken(new DeleteDeviceTokenEvent(deviceTokenId));

        if (!deviceTokenDetailsEvent.isEntityFound()) {
            return "redirect:/devices/";
        }
        redirectAttrs.addFlashAttribute("message",
                "Device disconnected");

        return "redirect:/devices/" + deviceTokenDetailsEvent.getDeviceTokenDetails().getDeviceId();
    }

    static List<Device> getDevices(AllDevicesEvent requestAllOEvents) {
        List<Device> devices = new ArrayList<>();

        for (DeviceDetails oEventDetails : requestAllOEvents.getDeviceDetailses()) {
            devices.add(Device.fromDeviceDetails(oEventDetails));
        }

        return devices;
    }*/
}
