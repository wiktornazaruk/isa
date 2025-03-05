import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { ApiService } from '../api.service';

@Component({
  selector: 'app-product-details',
  imports: [CommonModule],
  templateUrl: './product-details.component.html',
  styleUrl: './product-details.component.css',
  standalone: true,
})
export class ProductDetailsComponent implements OnInit {
  product: any = null;
  productName!: string;
  warehouseName!: string;

  constructor(
    private apiService: ApiService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.productName = this.route.snapshot.paramMap.get('productName') || '';
    this.warehouseName =
      this.route.snapshot.paramMap.get('warehouseName') || '';
    this.getProductDetails(this.productName);
  }

  getProductDetails(name: string): void {
    this.apiService.getProductDetails(name).subscribe(
      (data) => {
        this.product = data;
      },
      (error) => {
        console.error('Failed to fetch product details', error);
        alert('Unable to load product details.');
      }
    );
  }
}
