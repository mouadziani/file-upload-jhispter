import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { FileuploadSharedModule } from 'app/shared';
import { HOME_ROUTE, HomeComponent } from './';
// Added components
import { DetailsUploadComponent } from '../upload/details-upload/details-upload.component';
import { FormUploadComponent } from '../upload/form-upload/form-upload.component';
import { ListUploadComponent } from '../upload/list-upload/list-upload.component';

@NgModule({
    imports: [FileuploadSharedModule, RouterModule.forChild([HOME_ROUTE])],
    declarations: [
    	HomeComponent,
        // Added compoents
        DetailsUploadComponent,
        FormUploadComponent,
        ListUploadComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class FileuploadHomeModule {}
