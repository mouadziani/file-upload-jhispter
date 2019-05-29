import { NgModule } from '@angular/core';

import { FileuploadSharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent } from './';

@NgModule({
    imports: [FileuploadSharedLibsModule],
    declarations: [JhiAlertComponent, JhiAlertErrorComponent],
    exports: [FileuploadSharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent]
})
export class FileuploadSharedCommonModule {}
