import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ProductListComponent } from './product-list.component';
import { Router } from '@angular/router';
import { FormBuilder } from '@angular/forms';
import { ProductService } from '../service/product.service';
import { CategoryService } from '../service/category.service';
import { of } from 'rxjs';
import { NO_ERRORS_SCHEMA } from '@angular/core';

describe('ProductListComponent', () => {
  let component: ProductListComponent;
  let fixture: ComponentFixture<ProductListComponent>;
  let mockRouter: jasmine.SpyObj<Router>;

  beforeEach(async () => {
    mockRouter = jasmine.createSpyObj('Router', ['navigate']);

    await TestBed.configureTestingModule({
      declarations: [ProductListComponent],
      providers: [
        FormBuilder,
        { provide: Router, useValue: mockRouter },
        { provide: ProductService, useValue: { getAllProducts: () => of([]), deleteProduct: () => of({}) } },
        { provide: CategoryService, useValue: { getAllCategories: () => of([]) } }
      ],
      schemas: [NO_ERRORS_SCHEMA]
    }).compileComponents();

    fixture = TestBed.createComponent(ProductListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should navigate to add product page on addProduct()', () => {
    component.addProduct();
    expect(mockRouter.navigate).toHaveBeenCalledWith(['/product-add']);
  });

  it('should navigate to edit product page on editProduct()', () => {
    const testId = 1;
    component.editProduct(testId);
    expect(mockRouter.navigate).toHaveBeenCalledWith([`/product-edit/${testId}`]);
  });

  it('should navigate to product details page on productDetails()', () => {
    const testId = 1;
    component.productDetails(testId);
    expect(mockRouter.navigate).toHaveBeenCalledWith([`/product-details/${testId}`]);
  });


});