package com.az.demo.beanvalidation;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.validation.constraints.*;
import java.time.LocalDate;

@Named
@RequestScoped
public class User {

    @NotBlank(message = "Name should not be blank")
    @Size(min = 4, max = 10, message = "Name should be between 4 and 10 characters")
    private String name;

    @NotBlank(message = "Email should not be blank")
    @Email(message = "Invalid e-mail format")
    private String email;

    @NotNull(message = "Create date should not be null")
    @PastOrPresent(message = "Create date should be past or present")
    private LocalDate created;

    @NotNull(message = "Expires should not be null")
    @Future(message = "Expires should be a future date")
    private LocalDate expires;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getCreated() {
        return created;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
    }

    public LocalDate getExpires() {
        return expires;
    }

    public void setExpires(LocalDate expires) {
        this.expires = expires;
    }

    public void valid() {
        FacesContext
                .getCurrentInstance()
                .addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Your data is valid", ""));
    }
}
