import { Routes } from '@angular/router';
import  {DashbordComponent} from "./dashbord/dashbord.component";
import { LoginComponent} from "./login/login.component";
import  { LayoutComponent} from "./layout/layout.component";
import { DoctorComponent} from "./doctor/doctor.component";

export const routes: Routes = [

  {
    path: '',
    redirectTo:'login',
    pathMatch:'full'
  },

  {
    path:'login',
    component: LoginComponent
  },
  {
    path:'',
    component:LayoutComponent,
    children:[{
      path: 'dashboard',
      component: DashbordComponent
    }
    ]

  },

  {
    path: '', component: LayoutComponent, children: [{
      path: 'doctor', component: DoctorComponent
    }]

  }


];
