export class QuestDetail {
    id: number;
    challangeTitle: string;
    challangeDescription: string;
    ChallangeStatement: string;
    inputFormat: string;
    constraints: string;
    outputFormat: string;
    maxScore: number;
    maxRuntime: number;
    programmingLang: string;
    solutionUrl: string;
    level: number;
    rating: number;

    constructor(id: number, challangeTitle: string, challangeDescription: string, challangeStatement: string,
        inputFormat: string, constraints: string, outputFormat: string, maxScore: number, maxRuntime: number,
        programmingLang: string, solutionUrl: string, level: number, rating: number) {
        this.id = id;
        this.challangeTitle = challangeTitle;
        this.challangeDescription = challangeDescription;
        this.ChallangeStatement = challangeStatement;
        this.inputFormat = inputFormat;
        this.constraints = constraints;
        this.outputFormat = outputFormat;
        this.maxScore = maxScore;
        this.maxRuntime = maxRuntime;
        this.programmingLang = programmingLang;
        this.solutionUrl = solutionUrl;
        this.level = level;
        this.rating = rating;
    }
}
