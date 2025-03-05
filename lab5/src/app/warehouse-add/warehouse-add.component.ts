import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ApiService } from '../api.service';

@Component({
  selector: 'app-warehouse-add',
  templateUrl: './warehouse-add.component.html',
  styleUrls: ['./warehouse-add.component.css'],
  standalone: true,
  imports: [CommonModule, FormsModule],
})
export class WarehouseAddComponent {
  name: string = '';
  location: string = '';

  constructor(private apiService: ApiService, private router: Router) {}

  addWarehouse(): void {
    const warehouse = { name: this.name, location: this.location };
    this.apiService.addWarehouse(warehouse).subscribe(() => {
      this.router.navigate(['/warehouses/']);
    });
  }
}
