import { ActivatedRoute } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FileElement } from './folder-structure/directory/model/file-element';
import { FileService } from './folder-structure/directory/file.service';
import { Observable } from 'rxjs';
import { FilesService } from './files.service';
import { delay } from 'q';
import { AngularWaitBarrier } from 'blocking-proxy/built/lib/angular_wait_barrier';
import { STYLESHEET_MAP_PROVIDER } from '@angular/flex-layout';
import { environment } from './../../environments/environment';

@Component({
  selector: 'app-attempting-challenge',
  templateUrl: './attempting-challenge.component.html',
  styleUrls: ['./attempting-challenge.component.css']
})
export class AttemptingChallengeComponent implements OnInit {

  public fileElements: Observable<FileElement[]>;

  files: File[];

  currentRoot: FileElement;
  currentPath: string;
  canNavigateUp = false;

  parentData: string;

  private id: string;
  completechallenge;

  constructor(private http: HttpClient, private route: ActivatedRoute) { }

  ngOnInit() {
    this.id = this.route.snapshot.paramMap.get('id');
    this.http.get(environment.apiUrl + 'challenge/challengeAPI/v1/' + this.id).subscribe((res:any)=> {
    this.completechallenge = res;
    });
    console.log(JSON.parse(localStorage.getItem('currentUser')).username);
  }

}
