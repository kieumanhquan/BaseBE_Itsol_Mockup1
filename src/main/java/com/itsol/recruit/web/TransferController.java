package com.itsol.recruit.web;

import com.itsol.recruit.core.Constants;
import com.itsol.recruit.entity.ResponseObject;
import com.itsol.recruit.entity.Transfer;
import com.itsol.recruit.entity.User;
import com.itsol.recruit.repository.TransferRepository;
import com.itsol.recruit.service.EmailSenderService;
import com.itsol.recruit.service.TransferService;
import com.itsol.recruit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<List<Transfer>> findAll(){
        try {
            return ResponseEntity.ok(transferService.getAllTransfer());

        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    @PostMapping("transfer")
    public ResponseEntity<Transfer> createTransfer(@RequestBody Transfer transfer){
        try {
            transferService.createTranfer(transfer);
            User dm_old = userService.findDMByUnit(transfer.getEmployee().getUnit());
            emailSenderService.sendSimpleEmail(dm_old.getEmail(),"THÔNG BÁO ĐIỀU CHUYỂN", createBodyEmail(transfer));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    @PutMapping("transfer/{id}")
    public ResponseEntity<Transfer>  updateTransfer(@PathVariable("id") Integer transferId ,@RequestBody Transfer transfer){
        Transfer transferOld = transferService.getById(transferId);   
        try {
            if(transfer.getIsStatusOld() == 0 && transfer.getStatus() ==0 ){
                return ResponseEntity.ok(transferService.updateTransfer(transfer));
            }else if(transfer.getIsStatusOld() == 2 && transfer.getStatus() == 3){
                transferService.updateTransfer(transfer);
                //gui mail cho creator
                emailSenderService.sendSimpleEmail(transfer.getCreator().getEmail(),"THÔNG BÁO TỪ CHỐI ĐIỀU CHUYỂN", EmailTC(transfer));
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }else if(transfer.getIsStatusOld() == 2 && transfer.getIsStatusNew() ==0 && transfer.getStatus() == 2){
                transferService.updateTransfer(transfer);
                //gui mail cho dm 2
                User dmNew = userService.findDMByUnit(transfer.getEmployee().getUnit());
                emailSenderService.sendSimpleEmail(dmNew.getEmail(),"THÔNG BÁO ĐIỀU CHUYỂN", createBodyEmail(transfer));
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }else if(transfer.getIsStatusNew() == 2 && transfer.getStatus() == 3){
                transferService.updateTransfer(transfer);
                //gui mail cho creator
                emailSenderService.sendSimpleEmail(transfer.getCreator().getEmail(),"THÔNG BÁO TỪ CHỐI ĐIỀU CHUYỂN", EmailTC(transfer));
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
            else if(transfer.getIsStatusNew() == 1 && transfer.getStatus() == 1){
                transferService.updateTransfer(transfer);
                //thành công
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);

        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    @DeleteMapping("transfer/{id}")
    public void delete(@RequestParam("id") Integer id){
        transferService.deleteById(id);
    }
    @GetMapping("transfer/{id}")
    public ResponseEntity<Transfer> getTransferById(@PathVariable("id") Integer id){
        try {
            return ResponseEntity.ok(transferService.getById(id));

        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    @GetMapping( "transfer/test-pageffff")
    public ResponseEntity<ResponseObject> pageanSortTransfer(){
        System.out.println("hhhhh");
        Pageable pageable = PageRequest.of(0,2);
        return  ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(HttpStatus.OK,"",
                 transferRepository.searchTransfer(pageable,null,"ĐIỀU chuyển ",null,null,null,null)

        ));
    }






    private String createBodyEmail(Transfer transfer) {
        StringBuilder builder = new StringBuilder();
        builder.append("Thông báo đợt điều chuyển: \n");
        builder.append("Họ tên nhân viên: ");
        builder.append(transfer.getEmployee().getUserName());
        builder.append("\nĐơn vị cũ: ");
        builder.append(transfer.getUnitOld().getName());
        builder.append("\nĐơn vị sẽ chuyển tới : ");
        builder.append(transfer.getUnitNew().getName());
        builder.append("\n Người tạo đợt điều chuyển: ");
        builder.append(transfer.getCreator().getFullName());
        builder.append("\n Lý do chuyển: ");
        builder.append(transfer.getReasonTransfer());
        builder.append("\n Vui lòng thực hiện xét duyệt.: ");
//        builder.append("http://localhost:4200/admin/transfer-information/" + transfer.getId());
        return builder.toString();
    }
    private String EmailTC (Transfer transfer) {
        StringBuilder builder = new StringBuilder();
        builder.append("Thông báo đợt điều chuyển: \n");
        builder.append("Họ tên nhân viên: ");
        builder.append(transfer.getEmployee().getUserName());
        builder.append("\nĐơn vị cũ: ");
        builder.append(transfer.getUnitOld().getName());
        builder.append("\nĐơn vị sẽ chuyển tới : ");
        builder.append(transfer.getUnitNew().getName());
        builder.append("\n Người tạo đợt điều chuyển: ");
        builder.append(transfer.getCreator().getFullName());
        builder.append("\n Lý do chuyển: ");
        builder.append(transfer.getReasonTransfer());
        builder.append("\n Trạng thái.: ");
        if(transfer.getStatus() == 3 && transfer.getIsStatusOld() == 2 && transfer.getIsStatusNew() == 0){
            builder.append("Trưởng đơn vị:" + transfer.getUnitOld().getName()+ "đã từ chối");
        }else if(transfer.getStatus() == 3 && transfer.getIsStatusNew() == 2){
            builder.append("Trưởng đơn vị:" + transfer.getUnitNew().getName()+ "đã từ chối");
        }

        return builder.toString();
    }

}
