import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { HttpClientModule } from '@angular/common/http';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {AppComponent} from './app.component';
import {ProductListComponent} from './product-list/product-list.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import {ProductComponent} from './product/product.component';
import { AppRoutingModule } from './app-routing.module';
import {ProductFormComponent} from './product-form/product-form.component';
import {MatFormField, MatInputModule} from '@angular/material/input';
import {MatSelectModule} from '@angular/material/select';
import { RouterModule } from '@angular/router';
import {MatButtonModule} from '@angular/material/button';
import {MatTable, MatTableModule} from '@angular/material/table';
import {MatPaginatorModule} from '@angular/material/paginator';
import { HeaderComponent } from "./header/header.component";


@NgModule({
  declarations: [
    AppComponent,
    ProductListComponent,
    ProductComponent,
    ProductFormComponent,
    HeaderComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    NgbModule,
    AppRoutingModule,
    ReactiveFormsModule,
    MatFormField,
    MatSelectModule,
    RouterModule,
    MatInputModule,
    MatButtonModule,
    MatTableModule,
    MatPaginatorModule,
    MatButtonModule,
    
],
  providers: [],
  exports: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
