import { Component, OnInit } from '@angular/core';
import { DomSanitizer, SafeUrl, SafeResourceUrl } from '@angular/platform-browser';

@Component({
  selector: 'ngx-unauthorized',
  templateUrl: './unauthorized.component.html',
  styleUrls: ['./unauthorized.component.scss']
})
export class UnauthorizedComponent implements OnInit {

  safeSrc: SafeResourceUrl;
  audio = new Audio();
  constructor(private sanitizer: DomSanitizer) { 
    
    this.playAudio();
    this.safeSrc =  this.sanitizer.bypassSecurityTrustResourceUrl("https://www.youtube.com/embed/vI_ejPZkCFo");
  }

  playAudio(){
    
    this.audio.src = "/assets/data/palabra.mp3";
    this.audio.load();
    //this.audio.play();
    this.audio.loop = true;
    this.audio.volume = 0;
  }

  mute() {
    if (this.audio.volume !== 0) {
      this.audio.play();
      this.audio.volume = 0;
      
    } else {
      this.audio.play();
      this.audio.volume = 1;
    }
  }

  ngOnInit() {
  }



}
