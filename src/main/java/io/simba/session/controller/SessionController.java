package io.simba.session.controller;

import io.simba.session.dto.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@RestController
public class SessionController {
    @GetMapping("/session")
    public String session(HttpSession httpSession, @ModelAttribute User user) {
        httpSession.setAttribute("user:" + user.getId(), user);

        return "Success.";
    }

    @GetMapping("/session/attributeTypes")
    public Map getSessionAttributeTypes(HttpSession httpSession) {
        Enumeration<String> attributeNames = httpSession.getAttributeNames();

        Map<String, String> attributeMap = new HashMap<>();

        while (attributeNames.hasMoreElements()) {
            String attributeName = attributeNames.nextElement();

            attributeMap.put(attributeName, httpSession.getAttribute(attributeName).getClass().getName());
        }

        return attributeMap;
    }
}
