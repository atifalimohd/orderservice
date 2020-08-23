package com.oms.orderservice.model.reponse;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ErrorOrderResponse implements Serializable {

    private String message;
    private List<String> errorList;

}
