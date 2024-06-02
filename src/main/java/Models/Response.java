
package Models;

public class Response {
    int id_response;
    String responseText;

    public Response(int id_response, String responseText) {
        this.id_response = id_response;
        this.responseText = responseText;
    }

    public Response(String responseText) {
        this.responseText = responseText;
    }

    public int getId_response() {
        return id_response;
    }

    public void setId_response(int id_response) {
        this.id_response = id_response;
    }

    public String getResponseText() {
        return responseText;
    }

    public void setResponseText(String responseText) {
        this.responseText = responseText;
    }

    @Override
    public String toString() {
        return "Response{" +
                "id_response=" + id_response +
                ", responseText='" + responseText + '\'' +
                '}';
    }
}


