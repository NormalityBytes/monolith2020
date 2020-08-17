import { Moment } from 'moment';
import { IKontaktinfo } from 'app/shared/model/kontaktinfo.model';
import { IAdresse } from 'app/shared/model/adresse.model';

export interface IPerson {
  id?: number;
  prefix?: string;
  vorname?: string;
  nachname?: string;
  suffix?: string;
  geburtstag?: Moment;
  unternehmenId?: number;
  kontaktinfos?: IKontaktinfo[];
  adresses?: IAdresse[];
  beraterId?: number;
  kandidatId?: number;
  kontaktId?: number;
}

export class Person implements IPerson {
  constructor(
    public id?: number,
    public prefix?: string,
    public vorname?: string,
    public nachname?: string,
    public suffix?: string,
    public geburtstag?: Moment,
    public unternehmenId?: number,
    public kontaktinfos?: IKontaktinfo[],
    public adresses?: IAdresse[],
    public beraterId?: number,
    public kandidatId?: number,
    public kontaktId?: number
  ) {}
}
