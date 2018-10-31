import { EditorComponent } from './editor/editor.component';
import { DirectoryComponent } from './directory/directory.component';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { MonacoEditorModule } from 'ngx-monaco';
import { AppComponent } from './app.component';


@NgModule({
  declarations: [
    AppComponent,
    EditorComponent,
    DirectoryComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    MonacoEditorModule.forRoot()
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
