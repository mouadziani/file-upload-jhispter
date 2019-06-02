import { Component, OnInit, Input } from '@angular/core';
 
@Component({
  selector: 'jhi-details-upload',
  templateUrl: './details-upload.component.html']
})
export class DetailsUploadComponent implements OnInit {
 
  @Input() fileUpload: string;
 
  constructor() { }
 
  ngOnInit() {
  }
 
}
