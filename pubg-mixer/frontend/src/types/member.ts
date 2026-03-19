export interface Member {
  id?: number;
  nickname: string;
  tier: number;
  createdAt?: string;
}

export interface MemberCreateRequest {
  members: Member[];
}

