import { Component, inject, OnInit } from '@angular/core';
import { AuthService } from '../../services/AuthService';
import { Router, RouterModule } from '@angular/router';
import { AdminService } from '../../services/AdminService';
import { Utente } from '../../model/Utente';

@Component({
    selector: 'app-admin',
    imports: [RouterModule],
    templateUrl: './admin.component.html',
    styleUrl: './admin.component.css'
})
export class AdminComponent implements OnInit{
    
    private _authService = inject(AuthService);
    private _router = inject(Router)
    private _showUserTable:boolean = false;
    private _adminService = inject(AdminService);
    users:Utente[]=[];
    ngOnInit(): void {
        if(!(this._authService.isLogged())){
            this._router.navigate(['/home'])
        }
        this.populateUserList();
    }

    showUsertab(){
        (this._showUserTable)?this._showUserTable=false:this._showUserTable=true;
    }

    deleteCall(id:number){
        if(id.toString() == this._authService.getUserId()){
            alert('Non puoi cancellare te stesso')
        }else if(document.querySelector("#Auth-"+id)?.textContent?.trim()== "ADMIN")
            {
            if(window.confirm("sei sicuro di voler cancellare questo admin?"))
                {
                if(window.confirm("Giurin Giurello?"))
                    {
                    if(window.confirm("Giurissimo Giurissimello?"))
                        {
                        this._adminService.delete(id).subscribe
                        (
                            {
                            next: ()=> 
                                {
                                console.log("Successfuly deleted");
                                this.populateUserList();
                                this._router.navigate(['/admin'])
                                },
                            error:(e)=>
                                {
                                console.log("Unsuccessfuly deleted");
                                }
                            }
                        )
                        }
                    };
                }
        }else{
            this._adminService.delete(id).subscribe
            (
                {
                    next: ()=> 
                        {
                        console.log("Successfuly deleted");
                        this.populateUserList();
                        this._router.navigate(['/admin']);
                        },
                    error:(e)=>
                        {
                        console.log("Unsuccessfuly deleted");
                        }
                }
            )
    }
}

    promoteCall(id:number){
        if(document.querySelector("#Auth-"+id)?.textContent?.trim()== "ADMIN"){
            alert('non puoi promuovere un admin')
        }
        this._adminService.promote(id).subscribe({
            next: ()=> {
                console.log("Successfuly promoted");
                this.populateUserList();
            },
            error:(e)=>{
                console.log("Unsuccessfuly promoted");
            }
        });
    }

    populateUserList(){
        this._adminService.getAll().subscribe({
            next:(utente)=>{ 
                this.users = utente }
        })
    }

    get showuserTable(){
        return this._showUserTable;
    }


}
