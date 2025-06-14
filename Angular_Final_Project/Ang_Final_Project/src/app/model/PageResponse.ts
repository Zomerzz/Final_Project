export interface PageResponse<T> {
  content: T[];
  totalElements: number;
  // …altre proprietà di Page
}