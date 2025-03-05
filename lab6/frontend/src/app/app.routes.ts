import { Routes } from '@angular/router';
import { WarehouseListComponent } from './warehouse-list/warehouse-list.component';
import { WarehouseAddComponent } from './warehouse-add/warehouse-add.component';
import { WarehouseEditComponent } from './warehouse-edit/warehouse-edit.component';
import { WarehouseDetailsComponent } from './warehouse-details/warehouse-details.component';
import { ProductAddComponent } from './product-add/product-add.component';
import { ProductEditComponent } from './product-edit/product-edit.component';
import { ProductDetailsComponent } from './product-details/product-details.component';

export const routes: Routes = [
  { path: '', redirectTo: '/warehouses', pathMatch: 'full' },
  { path: 'warehouses', component: WarehouseListComponent },
  { path: 'warehouses/add', component: WarehouseAddComponent },
  { path: 'warehouses/edit/:name', component: WarehouseEditComponent },
  { path: 'warehouses/:name', component: WarehouseDetailsComponent },
  { path: 'warehouses/:name/products/add', component: ProductAddComponent },
  {
    path: 'warehouses/:warehouseName/products/edit/:productName',
    component: ProductEditComponent,
  },
  {
    path: 'warehouses/:warehouseName/products/:productName',
    component: ProductDetailsComponent,
  },
];
