import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule} from '@angular/forms';
import { RouterModule } from '@angular/router';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MatProgressBarModule, MatCardModule, MatIconModule, MatButtonModule, MatSnackBarModule} from '@angular/material';
import { HttpClientModule } from '@angular/common/http';
import { AppComponent } from './app.component';
import { CompileTriggerComponent } from './compile-trigger/compile-trigger.component';
import { ScoringComponent } from './scoring/scoring.component';
import { FeedbackComponent } from './feedback/feedback.component';
import { TestCasesComponent } from './test-cases/test-cases.component';

@NgModule({
  declarations: [
    AppComponent,
    CompileTriggerComponent,
    ScoringComponent,
    FeedbackComponent,
    TestCasesComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    RouterModule,
    BrowserAnimationsModule,
    MatProgressBarModule,
    MatCardModule,
    MatIconModule,
    MatButtonModule,
    MatSnackBarModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
