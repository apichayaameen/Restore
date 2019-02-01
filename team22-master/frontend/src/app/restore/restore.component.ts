import {Component, OnInit, ViewChild} from '@angular/core';
import { RestoreService } from '../service/restore.service';
import {MatSort} from '@angular/material';
import { HttpClient} from '@angular/common/http';
import {Router} from '@angular/router';
import {DatePipe} from '@angular/common';
import {MatSnackBar} from '@angular/material';


@Component ( {
  selector: 'app-restore',
  templateUrl: './restore.component.html',
  styleUrls: ['./restore.component.css']
  
})
export class RestoreComponent implements OnInit {

  displayedColumns1: string[] = ['customerIDs', 'leaseId', 'productName', 'reserveDate'
                                ,'returnDate','leaseStatus','status'];
  CurrentDate = new Date();

  Lease:Array<any>;
  reserveDate:Array<any>;
  returnDate:Array<any>;
  Product:Array<any>;
  productName:Array<any>;
  productStatus:Array<any>;
  customerIDs:Array<any>;
  customer:Array<any>;
  status:Array<any>;
  Restore:Array<any>;
  dateRestore:Array<any>;
  statusRestore:Array<any>;
  commentRestore:Array<any>;
  PayMents: Array<any>;
  pmId: Array<any>;
  datePay: Array<any>;
  typePay: Array<any>;

  views: any = {
    customerIDs: '',
    leaseId: '',
    productName: '',
    selectCustomerIDs: '',
    selectLeaseId: '',
    selectProductName: ''
  };

  pipe = new DatePipe('en-US');

  @ViewChild(MatSort)
  sort: MatSort;

  constructor(private restoreservice: RestoreService, private httpClient: HttpClient, private router: Router,private snackBar: MatSnackBar) {

  }

  ngOnInit() {
   this.restoreservice.getLease().subscribe(data => {
    this.Lease = data;
     console.log(this.Lease);
   });
   this.restoreservice.getCustomer().subscribe(data => {
     this.customer = data;
      console.log(this.customer);
     });
    this.restoreservice.getRestore().subscribe(data => {
     this.Restore = data;
      console.log(this.Restore);
     });
    this.restoreservice.getProduct().subscribe(data => {
     this.Product = data;
      console.log(this.Product);
     });

   }

  save() {
    this.httpClient.post('http://localhost:8080/restore/' + this.views.selectCustomerIDs + '/'
    + this.views.selectLeaseId + '/' + this.views.selectProductName + '/'
    + this.commentRestore, this.Restore)
      .subscribe(
        data => {
          console.log('POST Request is successful', data);
         //window.location.reload();
         this.snackBar.open('input detail ', 'complete', {
        });
        },
        error => {
          this.snackBar.open('input detail ', 'uncomplete', {
          });
          console.log('Error', error);
        }
      );

  }
   selectRow(row) {
   this.views.selectCustomerIDs = row.customer.customerIDs;
   this.views.selectLeaseId = row.leaseId;
   this.views.selectProductName = row.product.productName;
     console.log(this.views.selectCustomerIDs);
     console.log(this.views.selectLeaseId);
     console.log(this.views.selectProductName);
   }
}

