package com.itsol.recruit.web;

import com.itsol.recruit.core.Constants;
import com.itsol.recruit.dto.SortMultileColumm;
import com.itsol.recruit.dto.TransferDTO;
import com.itsol.recruit.entity.ResponseObject;
import com.itsol.recruit.entity.Role;
import com.itsol.recruit.entity.Transfer;
import com.itsol.recruit.entity.User;
import com.itsol.recruit.repository.TransferRepository;
import com.itsol.recruit.service.EmailSenderService;
import com.itsol.recruit.service.TransferService;
import com.itsol.recruit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = Constants.Api.Path.PUBLIC)
public class TransferController {
    @Autowired
    private TransferService transferService;
    @Autowired
    private EmailSenderService emailSenderService;
    @Autowired
    private UserService userService;
    @Autowired
    private TransferRepository transferRepository;

    @GetMapping("transfer")
    public ResponseEntity<List<Transfer>> findAll() {
        try {
            return ResponseEntity.ok(transferService.getAllTransfer());

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping("transfer")
    public ResponseEntity<Transfer> createTransfer(@RequestBody Transfer transfer) {
        try {
            transferService.createTranfer(transfer);
            User dm_old = userService.findDMByUnit(transfer.getEmployee().getUnit());
            emailSenderService.sendSimpleEmail(dm_old.getEmail(), "TH??NG B??O ??I???U CHUY???N", createBodyEmail(transfer));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping("transfer")
    public ResponseEntity<Transfer> updateTransfer(@RequestBody Transfer transfer) {
        try {
            User employee = transfer.getEmployee();
            User creator = transfer.getCreator();
            //X??c nh???n ??i???u chuy???n
            if (transfer.getStatus() == 2 && transfer.getIsStatusNew() == 0) {
                transferService.updateTransfer(transfer);
                employee.setUnit(transfer.getUnitNew());
                userService.update(employee);
            } else if (transfer.getStatus() == 2 && transfer.getIsStatusNew() == 1) {
                transferService.updateTransfer(transfer);
                employee.setUnit(transfer.getUnitNew());
                userService.update(employee);
            }
            //DM1 x??c nh???n
            if (transfer.getStatus() == 1 && transfer.getIsStatusOld() == 1) {
                User dm_new = userService.findDMByUnit(transfer.getUnitNew());
                emailSenderService.sendSimpleEmail(dm_new.getEmail(), "TH??NG B??O ??I???U CHUY???N", createBodyEmail(transfer));
            }
            //tu choi
            if (transfer.getStatus() == 2) {
                emailSenderService.sendSimpleEmail(creator.getEmail(), "TH??NG T??? CH???I ??I???U CHUY??N", EmailTC(transfer));
                transferService.updateTransfer(transfer);
            }

            return ResponseEntity.ok(transferService.updateTransfer(transfer));

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping("transfer/{id}")
    public void delete(@RequestParam("id") Integer id) {
        transferService.deleteById(id);
    }

    @GetMapping("transfer/{id}")
    public ResponseEntity<Transfer> getTransferById(@PathVariable("id") Integer id) {
        try {
            return ResponseEntity.ok(transferService.getById(id));

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("transfer/test-pageffff")
    public ResponseEntity<ResponseObject> pageanSortTransfer() {
        Pageable pageable = PageRequest.of(1, 5);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("true", "",
                transferRepository.searchTransfer(pageable, null, "??I???U chuy???n ", null, null, null, null)

        ));
    }

    @PutMapping("transfer/page-and-sort")
    public ResponseEntity<ResponseObject> getPageAndSort(@RequestParam int page,
                                                         @RequestParam Long userId,
                                                         @RequestParam int size,
                                                         @RequestBody TransferDTO transferDTO) {
        try {
            page = page < 0 ? 0 : page;
            Pageable pageable;
//            if (sortByValue.equals("undefined")) {
//                sortByValue = "createdDate";
//            }
            List<Sort.Order> orders = new ArrayList<>();
            List<SortMultileColumm> sortByValuesDTOList = transferDTO.getSortMultileColummList();
            if(sortByValuesDTOList.isEmpty()){
                orders.add(new Sort.Order(Sort.Direction.DESC, "createdDate") {
                });
            }else {
                sortByValuesDTOList.forEach(value ->{
                    orders.add(new Sort.Order(getSortDirection(value.getType()), value.getName()));
                });
            }
            pageable = PageRequest.of(page, size, Sort.by(orders));
            User user = userService.findById(userId);
            Set<Role> roles = user.getRoles();
            Set<String> listRole = roles.stream().map(role -> role.getCode()).collect(Collectors.toSet());
            Page<Transfer> pageTransfer;
            if (listRole.contains("ROLE_ADMIN") || listRole.contains("ROLE_HR")
                    || listRole.contains("ROLE_DM_HR")) {
                System.out.println("admin");
                pageTransfer = transferService.findTransfer(pageable
                        , null
                        , transferDTO.getName()
                        , transferDTO.getReason()
                        , transferDTO.getUnitOld()
                        , transferDTO.getUnitNew()
                        , transferDTO.getSuccessDay()
                );
                System.out.println(pageTransfer.toString());
                return ResponseEntity.status(HttpStatus.OK)
                        .body(new ResponseObject("ok", "Th??nh c??ng", pageTransfer));
            } else if (listRole.contains("ROLE_DM")) {
                System.out.println("dm");
                pageTransfer = transferService.findTransfer(pageable
                        , user.getUnit()
                        , transferDTO.getName()
                        , transferDTO.getReason()
                        , transferDTO.getUnitOld()
                        , transferDTO.getUnitNew()
                        , transferDTO.getSuccessDay()
                );
                return ResponseEntity.status(HttpStatus.OK)
                        .body(new ResponseObject("ok", "Th??nh c??ng", pageTransfer));
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseObject("false", "B???n kh??ng c?? quy???n xem th??ng tin", ""));

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseObject("false", "Kh??ng t??m th???y", ""));
        }
    }

    @PutMapping("transfer/review/{idUser}")
    public ResponseEntity<Transfer> reviewTransfer(@PathVariable("id") Long id, @RequestBody Transfer transfer) {
        try {
            User userReview = userService.findById(id);
            if (transfer.getStatus() == 2) {

            } else if (transfer.getStatus() == 1) {

            }
            return ResponseEntity.ok(transferService.updateTransfer(transfer));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    private String createBodyEmail(Transfer transfer) {
        StringBuilder builder = new StringBuilder();
        builder.append("Th??ng b??o ?????t ??i???u chuy???n: \n");
        builder.append("H??? t??n nh??n vi??n: ");
        builder.append(transfer.getEmployee().getUserName());
        builder.append("\n????n v??? c??: ");
        builder.append(transfer.getUnitOld().getName());
        builder.append("\n????n v??? s??? chuy???n t???i : ");
        builder.append(transfer.getUnitNew().getName());
        builder.append("\n Ng?????i t???o ?????t ??i???u chuy???n: ");
        builder.append(transfer.getCreator().getFullName());
        builder.append("\n L?? do chuy???n: ");
        builder.append(transfer.getReasonTransfer());
        builder.append("\n Vui l??ng th???c hi???n x??t duy???t.: ");
//        builder.append("http://localhost:4200/admin/transfer-information/" + transfer.getId());
        return builder.toString();
    }

    private String EmailTC(Transfer transfer) {
        StringBuilder builder = new StringBuilder();
        builder.append("Th??ng b??o ?????t ??i???u chuy???n: \n");
        builder.append("H??? t??n nh??n vi??n: ");
        builder.append(transfer.getEmployee().getUserName());
        builder.append("\n????n v??? c??: ");
        builder.append(transfer.getUnitOld().getName());
        builder.append("\n????n v??? s??? chuy???n t???i : ");
        builder.append(transfer.getUnitNew().getName());
        builder.append("\n Ng?????i t???o ?????t ??i???u chuy???n: ");
        builder.append(transfer.getCreator().getFullName());
        builder.append("\n L?? do chuy???n: ");
        builder.append(transfer.getReasonTransfer());
        builder.append("\n Tr???ng th??i.: ");
        if ( transfer.getIsStatusOld() == 2) {
            builder.append("Tr?????ng ????n v???:" + transfer.getUnitOld().getName() + "???? t??? ch???i");
        } else if (transfer.getIsStatusNew() == 2) {
            builder.append("Tr?????ng ????n v???:" + transfer.getUnitNew().getName() + "???? t??? ch???i");
        } else {
            builder.append("ADMIN ???? t??? ch???i");
        }
        return builder.toString();
    }
    private Sort.Direction getSortDirection(String direction) {
        if (direction.equals("asc")) {
            return Sort.Direction.ASC;
        } else if (direction.equals("desc")) {
            return Sort.Direction.DESC;
        }
        return Sort.Direction.ASC;
    }

}
