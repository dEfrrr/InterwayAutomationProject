import {Component, OnInit, ViewChild} from '@angular/core';
import {Product} from '../model/product';
import {ProductService} from '../service/product.service';
import {HttpErrorResponse} from '@angular/common/module.d-CnjH8Dlt';
import {Router} from '@angular/router';
import {MatTableDataSource} from '@angular/material/table';
import {MatPaginator} from '@angular/material/paginator';
import {FormBuilder, FormGroup} from '@angular/forms';
import {Category} from '../model/category';
import {CategoryService} from '../service/category.service';

@Component({
  selector: "app-product-list",
  templateUrl: "./product-list.component.html",
  styleUrls: ["./product-list.component.css"],
  standalone: false
})
export class ProductListComponent implements OnInit {
  displayedColumns: string[] = ['id', 'name', 'price', 'category', 'details', 'edit', 'delete'];
  products: Product[] = [];
  dataSource = new MatTableDataSource<Product>();
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  formValues!: FormGroup;
  categories: Category[] = [];

  constructor(private productService: ProductService,
              private route: Router,
              private fb: FormBuilder,
              private categoryService: CategoryService) {
  }

  ngOnInit(): void {
    this.getCategories();
    this.initFilterFormValues();
    this.getProducts();
  }

  getCategories() {
    this.categoryService.getAllCategories().subscribe({
      next: value => {
        this.categories = value;
      }
    })
  }

  getProducts(): void {
    this.productService.getAllProducts(this.formValues.getRawValue()).subscribe(
      (productList: Product[]) => {
        this.products = productList;
        this.dataSource = new MatTableDataSource<Product>(productList);
        this.dataSource.paginator = this.paginator;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  initFilterFormValues(): void {
    this.formValues = this.fb.group({
      name: [""],
      priceFrom: [0],
      priceTo: [99999],
      categoryId: [-1],
    })
  }

  addProduct() {
    this.route.navigate([`/product-add`]);
  }

  editProduct(id: number) {
    this.route.navigate([`/product-edit/${id}`]);
  }

  productDetails(id: number) {
    this.route.navigate([`/product-details/${id}`]);
  }

  deleteProduct(id: number) {
    this.productService.deleteProduct(id).subscribe(
      {
        error: err => {
          alert("There was an error deleting product with id " + id);
        },
        complete: () => {
          alert("Product deleted successfully");
          window.location.reload();
        }
      }
    )
  }
}
