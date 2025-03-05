import { Component, OnInit } from '@angular/core';
import { ApiService } from '../api.service';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-warehouse-list',
  templateUrl: './warehouse-list.component.html',
  styleUrls: ['./warehouse-list.component.css'],
  standalone: true,
  imports: [CommonModule],
})
export class WarehouseListComponent implements OnInit {
  warehouses: any[] = [];

  constructor(private apiService: ApiService, private router: Router) {}

  ngOnInit(): void {
    this.loadWarehouses();
  }

  loadWarehouses(): void {
    this.apiService.getWarehouses().subscribe((data) => {
      this.warehouses = data;
    });
  }

  navigateToAddWarehouse(): void {
    this.router.navigate(['/warehouses/add']);
  }

  navigateToWarehouseDetails(name: string): void {
    this.router.navigate([`/warehouses/${name}`]);
  }

  navigateToEditWarehouse(name: string): void {
    this.router.navigate([`/warehouses/edit/${name}`]);
  }

  deleteWarehouse(name: string): void {
    if (
      confirm(
        'Are you sure to delete warehouse ' + name + ', and all its products?'
      )
    ) {
      this.apiService.deleteWarehouse(name).subscribe(() => {
        this.loadWarehouses();
      });
    }
  }
}
