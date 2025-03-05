import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { ApiService } from '../api.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-warehouse-edit',
  imports: [CommonModule, FormsModule],
  templateUrl: './warehouse-edit.component.html',
  styleUrl: './warehouse-edit.component.css',
  standalone: true,
})
export class WarehouseEditComponent implements OnInit {
  warehouseName!: string;
  warehouse: any = null;

  constructor(
    private apiService: ApiService,
    private router: Router,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.warehouseName = this.route.snapshot.paramMap.get('name') || '';
    this.getWarehouseDetails(this.warehouseName);
  }

  getWarehouseDetails(name: string): void {
    this.apiService.getWarehouseDetails(name).subscribe((data) => {
      this.warehouse = { ...data };
    });
  }

  updateWarehouse(): void {
    const updatedWarehouse = {
      ...this.warehouse,
    };

    this.apiService
      .updateWarehouse(this.warehouseName, updatedWarehouse)
      .subscribe(() => {
        this.router.navigate(['/warehouses']);
      });
  }
}
