package org.group18.hotelbooking.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerDTO {

    private Long customerId;
    private String name;
    private String email;
    private String phoneNumber;
    private String address;

    public CustomerDTO() {
    }

    public CustomerDTO(Long customerId, String name, String email, String phoneNumber, String address) {
        this.customerId = customerId;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }
}
