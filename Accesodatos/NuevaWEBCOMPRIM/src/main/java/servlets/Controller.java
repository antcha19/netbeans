package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Controller
 */
@WebServlet("/Controller")
public class Controller extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String op = request.getParameter("op");
        String url = "";

        //case = es la variable para acceder desde el index.html
        //url es el nombre que ponemos en la respectiva clase de los paquetes servlets
        switch (op) {
            case "doAltaEwallet":
                url = "AltaEwallet";
                break;
            case "toNuevoEwallet":
                url = "nuevoEwallet.html";
                break;
            //hacer link que apunta index.html
            case "HacerCompra":
                url = "CompraNueva.html";
                break;

            case "AltaCompra":
                url = "AltaCompra";
                break;

            case "doEliminar":
                url = "EliminarAction";
                break;
            case "doRecuperar":
                url = "RecuperadorWallet";
                break;
            case "Recuperarproducto":
                url = "RecuperarPRO";
                break;
            case "eliminarpro":
                url = "EliminarPRO";
                break;
            case "RecuperarCompra":
                url = "RecuperarCOM";
                break;
            case "RecuperarDevolucion":
                url = "RecuperarDEVO";
                break;
            
            //realizamo una nueva wallet   

        }

        request.getRequestDispatcher(url).forward(request, response);
    }

}
