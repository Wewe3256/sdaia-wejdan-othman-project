import {Component} from '@angular/core';
import {Router} from "@angular/router";
import {FormsModule} from "@angular/forms";

@Component({
    selector: 'app-login',
    standalone: true,
    imports: [FormsModule],
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.css']
})
export class LoginComponent {
    loginObject: Login;

    constructor(private router: Router) {
        this.loginObject = new Login();
    }

    onLogin(): void {

        // this.http.get('http://dev-1261.mshome.net:8081/FinaliProject_1/webapi/secures').subscribe(loninResponse =>{
        //   this.LoginResult = loninResponse.toString();
        // })
        //
        //
        // if(this.LoginObject.username === 'success'){
        //   alert("Login successful");
        //   this.router.navigateByUrl('/dashboard');
        // }else  {
        //   alert("invalid username or password");
        // }

        if (this.loginObject.username === 'admin' && this.loginObject.password == 'admin') {
            alert("login successful");
            this.router.navigateByUrl('/dashboard');
        } else {
            alert("invaled username or password ")
        }

    }

}

export class Login {
    username: string;
    password: string;

    constructor() {
        this.username = '';
        this.password = '';
    }

}
