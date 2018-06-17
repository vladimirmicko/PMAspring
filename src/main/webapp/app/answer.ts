import { Result }  from './result.js';


export class Answer {
  id: number;
  slideName: string;
  answerNumber: number;
  answerTime: number;
  primeStimShowTime: number;
  testStimShowTime: number;
  answerValue: number;
  result: Result;
  answerDuration: number;
  primeStimDuration: number;
}

