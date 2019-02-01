package com.team22.backend.Controller;
import com.team22.backend.Entity.*;
import com.team22.backend.Repository.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class RestoreController {
    @Autowired
    private final RestoreRepository restoreRepository;
    @Autowired
    private LeaseRepository leaseRepository;
    @Autowired
    private PayMentRepository paymentRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ProductRepository productRepository;

    public RestoreController (RestoreRepository restoreRepository, 
                                    LeaseRepository leaseRepository, 
                                   PayMentRepository paymentRepository,
                                   CustomerRepository customerRepository,
                                   ProductRepository productRepository) {
        this.restoreRepository = restoreRepository;
        this.leaseRepository = leaseRepository;
        this.paymentRepository = paymentRepository;
        this.customerRepository = customerRepository;
        this.productRepository =  productRepository;
    }

    @GetMapping("/rlease")
        public Collection<Lease>Rlease() {
            return leaseRepository.findAll().stream()
            .filter(this::isRlease)
            .collect(Collectors.toList());
        }
        private boolean isRlease(Lease lease){
            return lease.getStatus().equals("paid");
        }
    
    @GetMapping("/rproduct")
    public Collection<Product> product() {
        return productRepository.findAll().stream().collect(Collectors.toList());
    }

    @GetMapping("/rrestore/{id}")
        public Collection<PayMent> payment(@PathVariable String id) {
            return paymentRepository.findAll().stream()
            .filter((s) -> "paid".equals(s.getStatusPay()) && id.equals(s.getCustomer().getCustomerIDs()) && "Renting".equals(s.getTypePay()))
            .collect(Collectors.toList());
        }
    
    @GetMapping("/Rlease")
        public Collection<Lease> lease() {
            return leaseRepository.findAll().stream()
            .filter(this::isLease)
            .collect(Collectors.toList());
        }
        private boolean isLease(Lease lease){
            return lease.getLeaseStatus().equals("Rent");
        }
    
    @GetMapping("/restore")
        public Collection<Restore> restore() {
            return restoreRepository.findAll().stream()
                    .filter(this::isRestore)
                    .collect(Collectors.toList());
        }
        private boolean isRestore(Restore restore){
            return restore.getStatusRestore().equals("Restore");
   }
    @GetMapping("/rcustomer")
        public Collection<Customer> customer() {
            return customerRepository.findAll().stream()
            .collect(Collectors.toList());
    }

    // @PostMapping("/restore/{leaseId}/{commentRestore}/{customerIDs}/{productName}")
    //  public Restore newRestore(@PathVariable Long leaseId,@PathVariable String commentRestore,
    //                            @PathVariable String customerIDs,  @PathVariable String productName){
                                     

    //          Restore newRestore = new Restore();
    //       //newRestore.setStatusRestore(statusRestore);
    //       newRestore.setCommentRestore(commentRestore);
    //       Date dateRestore= new Date();
    //       newRestore.setDateRestore(dateRestore);
    //       Customer customerid = customerRepository.findByCustomerIDs(customerIDs);
    //       newRestore.setCustomer(customerid);
    //       Lease lease = leaseRepository.findByLeaseId(leaseId);
    //       newRestore.setLease(leaseId);
    //       return restoreRepository.save(newRestore); 

    //  }
//      @PutMapping("/lease/{id}/{leaseStatus}")
//     Lease replaceLease(Lease newLease, @PathVariable String leaseStatus, @PathVariable Long id){

//     return leaseRepository.findById(id)
//                 .map(lease ->{
//                 lease.setLeaseStatus(leaseStatus);
//                 return leaseRepository.save(lease);
//             }
//             ).orElseGet(() ->{
//                 newLease.setLeaseId(id);
//                 return leaseRepository.save(newLease);
//     });
// }
}
