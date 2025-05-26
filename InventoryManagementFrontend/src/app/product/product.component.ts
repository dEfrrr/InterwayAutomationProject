import {Component, OnInit} from '@angular/core';
import {ProductService} from '../service/product.service';
import {ActivatedRoute} from '@angular/router';
import {Product} from '../model/product';
import {HttpErrorResponse} from '@angular/common/module.d-CnjH8Dlt';
import {Category} from '../model/category';

@Component({
  selector: "app-product",
  templateUrl: "./product.component.html",
  styleUrls: ["./product.component.css"],
  standalone: false
})
export class ProductComponent implements OnInit{
  product: Product = {
    id: 0,
    name: '',
    description: '',
    price: 0,
    quantityInStock: 0,
    category: {} as Category
  };


  constructor(private productService: ProductService, private route: ActivatedRoute,) {
  }

  ngOnInit(): void {
    this.getProduct();
  }

  getProduct(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));

    this.productService.getProduct(id).subscribe(
      (product: Product) => {
        this.product = product;
      },
      (error: HttpErrorResponse) => {
        alert(error.message)
      }
    );
  }

}
