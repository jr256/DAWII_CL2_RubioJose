package pe.edu.cibertec.DAWIICL2RubioJose.controller.frontoffice;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LogOutController {

    @GetMapping("/cerrar-sesion")
    public String cerrarSesion(HttpServletRequest request) {
        request.getSession().invalidate();
        return "frontoffice/auth/frmLogin";
    }
}
