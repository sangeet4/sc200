import { Challenge } from './challenge';

export class Profile {
    email: string;
    firstName: string;
    lastName: string;
    username: string;
    phone: number;
    score: number;
    ranking: number;
    challengeAttempted: Challenge[];
    challengeCreated: Challenge[];
    challengeUpvoted: Challenge[];
    challengeDownvoted: Challenge[];
}
