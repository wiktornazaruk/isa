import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { CommonModule } from '@angular/common';
import { ApiService } from '../api.service';
import { forkJoin } from 'rxjs';

@Component({
  selector: 'app-warehouse-details',
  imports: [CommonModule],
  templateUrl: './warehouse-details.component.html',
  styleUrl: './warehouse-details.component.css',
  standalone: true,
})
export class WarehouseDetailsComponent implements OnInit {
  warehouse: any = {};
  warehouseName!: string;
  products: any[] = []; // All products
  filteredProducts: any[] = []; // Products in the current warehouse

  constructor(
    private apiService: ApiService,
    private router: Router,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.warehouseName = this.route.snapshot.paramMap.get('name') || '';
    this.getWarehouseDetails(this.warehouseName);
    this.loadProducts();
  }

  loadProducts(): void {
    // Fetch the shallow list of products
    this.apiService
      .getProducts()
      .subscribe((shallowProducts: { id: number; name: string }[]) => {
        const productDetailRequests = shallowProducts.map((product) =>
          this.apiService.getProductDetails(product.name)
        );

        // Use forkJoin to wait for all product details to load
        forkJoin(productDetailRequests).subscribe((detailedProducts: any[]) => {
          this.products = detailedProducts;

          // Update filtered products based on the current warehouse
          this.updateFilteredProducts();
        });
      });
  }

  updateFilteredProducts(): void {
    // Filter products for the current warehouse
    this.filteredProducts = this.products.filter(
      (product) => product.warehouseName === this.warehouseName
    );
  }

  getWarehouseDetails(name: string): void {
    this.apiService.getWarehouseDetails(name).subscribe((data) => {
      this.warehouse = data;
    });
  }

  navigateToProductDetails(name: string): void {
    this.router.navigate([`warehouses/${this.warehouseName}/products/${name}`]);
  }

  navigateToEditProduct(name: string): void {
    this.router.navigate([
      `warehouses/${this.warehouseName}/products/edit/${name}`,
    ]);
  }

  deleteProduct(name: string): void {
    if (confirm('Are you sure you want to delete the product ' + name + '?')) {
      this.apiService.deleteProduct(name).subscribe(() => {
        // Remove the deleted product locally
        this.products = this.products.filter(
          (product) => product.name !== name
        );

        // Update the filtered products for the warehouse
        this.updateFilteredProducts();
      });
    }
  }

  navigateToAddProduct(): void {
    this.router.navigate([`warehouses/${this.warehouseName}/products/add/`]);
  }
}
