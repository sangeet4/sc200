export class QuestDetail {
    id: string;
    userId: string;
    challengeTitle: string;
    challengeDescription: string;
    challengeStatement: string;
    inputFormat: string;
    constraints: string;
    outputFormat: string;
    maxScore: number;
    maxRuntime: number;
    programmingLang: string;
    topic: string;
    solutionUrl: string;
    boilerPlateUrl: string;
    level: number;
    rating: number;

    constructor() {
        this.id = '';
        this.userId = '';
        this.challengeTitle = '';
        this.challengeDescription = '';
        this.challengeStatement = '';
        this.inputFormat = '';
        this.constraints = '';
        this.outputFormat = '';
        this.maxScore = null;
        this.maxRuntime = null;
        this.programmingLang = '';
        this.topic = '';
        this.solutionUrl = '';
        this.boilerPlateUrl = '';
        this.level = null;
        this.rating = null;
    }
}
