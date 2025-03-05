import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ApiService } from '../api.service';

@Component({
  selector: 'app-product-add',
  imports: [CommonModule, FormsModule],
  templateUrl: './product-add.component.html',
  styleUrl: './product-add.component.css',
  standalone: true,
})
export class ProductAddComponent {
  constructor(
    private apiService: ApiService,
    private router: Router,
    private route: ActivatedRoute
  ) {}
  name!: string;
  price!: number;
  quantity!: number;
  category: string = '';
  warehouseName: string = '';

  ngOnInit(): void {
    this.warehouseName = this.route.snapshot.paramMap.get('name') || '';
  }

  addProduct(): void {
    const product = {
      name: this.name,
      price: this.price,
      quantity: this.quantity,
      category: this.category,
      warehouseName: this.warehouseName,
    };
    this.apiService.addProduct(product).subscribe(() => {
      this.router.navigate([`warehouses/${this.warehouseName}/`]);
    });
  }
}
