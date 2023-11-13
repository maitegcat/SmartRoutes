package urjc.com.wayfindingapp.Model;

public class Balizalocal  {


    private static String name;
    private static String codletra;
    private static Float coord_x;
    private static Float coord_y;


    public Balizalocal(String string, float aFloat,float aFloat1, String string1) {
    }


    public static String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static String getCodLetra() {
        return codletra;
    }

    public void setCodletra(String codletra) {
        this.codletra = codletra;
    }

    public static Float getCoord_x() {
        return coord_x;
    }

    public void setCoord_x(Float coord_x) {
        this.coord_x = coord_x;
    }

    public static Float getCoord_y() {
        return coord_y;
    }

    public void setCoord_y(Float coord_y) {
        this.coord_y = coord_y;
    }


 /*   // Estructura JSON para crear xml
    public String  jsonString (){
        return " { \"name\": \n" +
                " \"letra\": \"coord_x\": \"coord_y\": \n }";
         }*/
}
