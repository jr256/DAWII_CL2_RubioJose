package pe.edu.cibertec.DAWIICL2RubioJose.controller.frontoffice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pe.edu.cibertec.DAWIICL2RubioJose.model.bd.Usuario;
import pe.edu.cibertec.DAWIICL2RubioJose.service.UsuarioService;

@Controller
@RequestMapping("/cambiar-clave")
public class ResetPassController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public String mostarForm() {
        return "frmCambioClave";
    }

    @PostMapping
    public String cambiarContrasena(
            @RequestParam("nuevaClave") String nuevaClave,
            @RequestParam("claveConfirmada") String claveConfirmada,
            @AuthenticationPrincipal UserDetails userDetails,
            RedirectAttributes redirectAttributes) {

        if (nuevaClave.equals(claveConfirmada)) {
            String username = userDetails.getUsername();
            Usuario usuario = usuarioService.findUserByUserName(username);

            // Actualizar la contraseña en la base de datos
            usuario.setPassword(new BCryptPasswordEncoder().encode(nuevaClave));
            usuarioService.saveUser(usuario);

            redirectAttributes.addFlashAttribute("mensaje", "Password cambiado con éxito");
        } else {
            redirectAttributes.addFlashAttribute("error", "Los passwords no coinciden.");
        }

        return "redirect:/cambiar-clave";
    }
}
