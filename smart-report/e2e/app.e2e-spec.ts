import { SmartReportPage } from './app.po';

describe('smart-report App', () => {
  let page: SmartReportPage;

  beforeEach(() => {
    page = new SmartReportPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
