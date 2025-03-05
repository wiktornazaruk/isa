import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { ApiService } from '../api.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-product-edit',
  imports: [CommonModule, FormsModule],
  templateUrl: './product-edit.component.html',
  styleUrl: './product-edit.component.css',
  standalone: true,
})
export class ProductEditComponent implements OnInit {
  warehouseName!: string;
  productName!: string;
  product: any = null;

  constructor(
    private apiService: ApiService,
    private router: Router,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.productName = this.route.snapshot.paramMap.get('productName') || '';
    this.warehouseName =
      this.route.snapshot.paramMap.get('warehouseName') || '';
    this.getProductDetails(this.productName);
  }

  getProductDetails(name: string): void {
    this.apiService.getProductDetails(name).subscribe((data) => {
      this.product = { ...data };
    });
  }

  updateProduct(): void {
    const updatedProduct = {
      ...this.product,
    };

    this.apiService
      .updateProduct(this.productName, updatedProduct)
      .subscribe(() => {
        this.router.navigate([`/warehouses/${this.warehouseName}`]);
      });
  }
}
