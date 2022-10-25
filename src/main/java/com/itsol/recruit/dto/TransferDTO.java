package com.itsol.recruit.dto;

import com.itsol.recruit.entity.Unit;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TransferDTO {
    private Unit unitDm;
    private String name;
    private String reason;
    private Unit unitOld;
    private Unit unitNew;
    private Date successDay;
    List<SortMultileColumm> sortMultileColummList;

}
