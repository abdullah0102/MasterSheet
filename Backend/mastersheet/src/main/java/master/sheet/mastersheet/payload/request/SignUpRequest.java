package master.sheet.mastersheet.payload.request;

import java.util.Date;

import javax.validation.constraints.*;


public class SignUpRequest {
    @NotEmpty()
    private String username;
    @NotEmpty()
    @Email
    private String email;
    private String BirthDate;
    private String first_name;
    private String last_name;
    private int role;
    private String display_name;
    

    @Override
    public String toString() {
        return "{" +
            " username='" + getUsername() + "'" +
            ", email='" + getEmail() + "'" +
            ", BirthDate='" + getBirthDate() + "'" +
            ", first_name='" + getFirst_name() + "'" +
            ", last_name='" + getLast_name() + "'" +
            ", role='" + getRole() + "'" +
            ", display_name='" + getDisplay_name() + "'" +
            "}";
    }

    /**
     * @return String return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return String return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return Date return the BirthDate
     */
    public String getBirthDate() {
        return BirthDate;
    }

    /**
     * @param BirthDate the BirthDate to set
     */
    public void setBirthDate(String BirthDate) {
        this.BirthDate = BirthDate;
    }

    /**
     * @return String return the first_name
     */
    public String getFirst_name() {
        return first_name;
    }

    /**
     * @param first_name the first_name to set
     */
    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    /**
     * @return String return the last_name
     */
    public String getLast_name() {
        return last_name;
    }

    /**
     * @param last_name the last_name to set
     */
    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    /**
     * @return int return the role
     */
    public int getRole() {
        return role;
    }

    /**
     * @param role the role to set
     */
    public void setRole(int role) {
        this.role = role;
    }

    /**
     * @return String return the display_name
     */
    public String getDisplay_name() {
        return display_name;
    }

    /**
     * @param display_name the display_name to set
     */
    public void setDisplay_name(String display_name) {
        this.display_name = display_name;
    }

}
