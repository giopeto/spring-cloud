import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { catchError } from 'rxjs/operators';
import 'rxjs/add/observable/of';

import { Groups } from '../models/Groups';
import { API_BASE_URL, HTTP_OPTIONS, HTTP_OPTIONS_MULTIPART } from '../common/ApiConstants';

@Injectable()
export class FilesService {

	private apiUrl = `${API_BASE_URL}/files`;
	formData: FormData = new FormData();
	
	constructor(private http: HttpClient) {}

	getFilesUrl() {
		return this.apiUrl;
	}	

	save(domainId) {
		if (this.formData.get('files')) {
			this.updateFormData('domainId', domainId);
			return this.http.post<any>(`${this.apiUrl}`, this.formData, HTTP_OPTIONS_MULTIPART);
		} else {
			return Observable.of([])
		}
	}

	getByDomainId(domainId) {
		return this.http.get<any>(`${this.apiUrl}/${domainId}`, HTTP_OPTIONS);
	}

	remove({domainId, id}) {
		return this.http.delete(`${this.apiUrl}/${id}/domainId/${domainId}`, HTTP_OPTIONS);
	}

	resetFormData() {
		this.formData = new FormData();
	}

	private updateFormData(name, payload) {
		this.formData.append(name, payload);
	}
}
