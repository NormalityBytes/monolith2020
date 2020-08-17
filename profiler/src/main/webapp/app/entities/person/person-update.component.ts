import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IPerson, Person } from 'app/shared/model/person.model';
import { PersonService } from './person.service';
import { IUnternehmen } from 'app/shared/model/unternehmen.model';
import { UnternehmenService } from 'app/entities/unternehmen/unternehmen.service';

@Component({
  selector: 'jhi-person-update',
  templateUrl: './person-update.component.html',
})
export class PersonUpdateComponent implements OnInit {
  isSaving = false;
  unternehmen: IUnternehmen[] = [];

  editForm = this.fb.group({
    id: [],
    prefix: [null, [Validators.maxLength(20)]],
    vorname: [null, [Validators.required, Validators.maxLength(30)]],
    nachname: [null, [Validators.required, Validators.maxLength(30)]],
    suffix: [null, [Validators.maxLength(20)]],
    geburtstag: [],
    unternehmenId: [],
  });

  constructor(
    protected personService: PersonService,
    protected unternehmenService: UnternehmenService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ person }) => {
      if (!person.id) {
        const today = moment().startOf('day');
        person.geburtstag = today;
      }

      this.updateForm(person);

      this.unternehmenService
        .query({ filter: 'person-is-null' })
        .pipe(
          map((res: HttpResponse<IUnternehmen[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IUnternehmen[]) => {
          if (!person.unternehmenId) {
            this.unternehmen = resBody;
          } else {
            this.unternehmenService
              .find(person.unternehmenId)
              .pipe(
                map((subRes: HttpResponse<IUnternehmen>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IUnternehmen[]) => (this.unternehmen = concatRes));
          }
        });
    });
  }

  updateForm(person: IPerson): void {
    this.editForm.patchValue({
      id: person.id,
      prefix: person.prefix,
      vorname: person.vorname,
      nachname: person.nachname,
      suffix: person.suffix,
      geburtstag: person.geburtstag ? person.geburtstag.format(DATE_TIME_FORMAT) : null,
      unternehmenId: person.unternehmenId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const person = this.createFromForm();
    if (person.id !== undefined) {
      this.subscribeToSaveResponse(this.personService.update(person));
    } else {
      this.subscribeToSaveResponse(this.personService.create(person));
    }
  }

  private createFromForm(): IPerson {
    return {
      ...new Person(),
      id: this.editForm.get(['id'])!.value,
      prefix: this.editForm.get(['prefix'])!.value,
      vorname: this.editForm.get(['vorname'])!.value,
      nachname: this.editForm.get(['nachname'])!.value,
      suffix: this.editForm.get(['suffix'])!.value,
      geburtstag: this.editForm.get(['geburtstag'])!.value ? moment(this.editForm.get(['geburtstag'])!.value, DATE_TIME_FORMAT) : undefined,
      unternehmenId: this.editForm.get(['unternehmenId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IPerson>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }

  trackById(index: number, item: IUnternehmen): any {
    return item.id;
  }
}
