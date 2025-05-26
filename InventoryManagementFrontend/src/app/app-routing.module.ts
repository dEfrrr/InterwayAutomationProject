import { NgModule } from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {ProductListComponent} from './product-list/product-list.component';
import {ProductComponent} from './product/product.component';
import {ProductFormComponent} from './product-form/product-form.component';

const routes: Routes = [
  { path: '', component: ProductListComponent },
  { path: 'products', component: ProductListComponent },
  { path: 'product-details/:id', component: ProductComponent },
  { path: 'product-add', component: ProductFormComponent },
  { path: 'product-edit/:id', component: ProductFormComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
