import {Component, OnInit} from '@angular/core';
import {ProductService} from '../service/product.service';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {HttpErrorResponse} from '@angular/common/module.d-CnjH8Dlt';
import {Product} from '../model/product';
import {CategoryService} from '../service/category.service';
import {Category} from '../model/category';

@Component({
  selector: "app-product-form",
  templateUrl: "./product-form.component.html",
  styleUrls: ["./product-form.component.css"],
  standalone: false
})
export class ProductFormComponent implements OnInit {
  productForm!: FormGroup;
  categories: Category[] = [];

  constructor(private productService: ProductService,
              private categoryService: CategoryService,
              private fb: FormBuilder,
              private router: Router,
              private route: ActivatedRoute) {

  }


  
  ngOnInit(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    this.getCategories();

    if (id !== null && id !== undefined && id !== 0){
      this.productService.getProduct(id).subscribe(
        (product: Product) => {
          this.patchForm(product);
        },
        (error: HttpErrorResponse) => {
          alert(error.message)
        }
      );
    } else {
      this.initForm();
    }
  }

  getCategories() {
    this.categoryService.getAllCategories().subscribe({
      next: value => {
        this.categories = value;
      }
    })
  }

  initForm(): void {
    this.productForm = this.fb.group({
      name: ["", Validators.required],
      description: [""],
      price: [0, [Validators.required, Validators.min(0)]],
      quantityInStock: [0, [Validators.required, Validators.min(0)]],
      category: [0],
    })
  }

  patchForm(product: Product): void {
    this.productForm = this.fb.group({
      name: [product.name, Validators.required],
      description: [product.description],
      price: [product.price, [Validators.required, Validators.min(0)]],
      quantityInStock: [product.quantityInStock, [Validators.required, Validators.min(0)]],
      category: [product.category.id],
    })
  
  }


  return(){
    this.router.navigate(['/products']);
  }

  submitForm(): void {
    let productToAdd = this.productForm.getRawValue();

    if (this.productForm.valid) {
      const id = Number(this.route.snapshot.paramMap.get('id'));
      if (id) {
        this.productService.editProduct(id, productToAdd).subscribe(
        () => {

        },
        (error: HttpErrorResponse) => {
          alert("Error saving product changes");
        },
        () => {
          alert("Successfully saved product changes");
          this.router.navigate(['/products']);
        }
      )
    } else {
        this.productService.addProduct(productToAdd).subscribe(
          () => {

          },
          (error: HttpErrorResponse) => {
            alert("Error adding product");
          },
          () => {
            alert("Successfully added product");
            this.router.navigate(['/products']);
          }
        );
      }
    }
  }
}
