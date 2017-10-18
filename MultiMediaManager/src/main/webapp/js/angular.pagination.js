var myModule = angular.module("myModule", []);
myModule.constant('pagexConfig', {
    visiblePageCount: 10,
    firstText: 'First',
    lastText: 'Last',
    prevText: 'Previous',
    nextText: 'Next'
}).directive("pager",function(pagexConfig) {
    return {
        link: function(scope, element, attrs) {
            var visiblePageCount = angular.isDefined(attrs.visiblePageCount) ? attrs.visiblePageCount : pagexConfig.visiblePageCount;
            scope.firstText = angular.isDefined(attrs.firstText) ? attrs.firstText : pagexConfig.firstText;
            scope.lastText = angular.isDefined(attrs.lastText) ? attrs.lastText : pagexConfig.lastText;
            scope.prevText = angular.isDefined(attrs.prevText) ? attrs.prevText : pagexConfig.prevText;
            scope.nextText = angular.isDefined(attrs.nextText) ? attrs.nextText : pagexConfig.nextText;
            scope.currentPage = 1;
            scope.pageChange = function(page) {
                if (page >= 1 && page <= scope.pageCount) {
                    scope.currentPage = page;
                } else {
                    scope.currentPage = 1;
                }
            }

    function build() {
		var low, high, v;
	
		scope.pagenums = [];
	
		if (scope.pageCount == 0) {
			return;
		}
		if (scope.currentPage > scope.pageCount) {
			scope.currentPage = 1;
		}
	
		if (scope.pageCount <= visiblePageCount) {
			low = 1;
			high = scope.pageCount;
		} else {
			v = Math.ceil(visiblePageCount / 2);
			low = Math.max(scope.currentPage - v, 1);
			high = Math.min(low + visiblePageCount - 1,
					scope.pageCount);
	
			if (scope.pageCount - high < v) {
				low = high - visiblePageCount + 1;
			}
		}
	
		for (; low <= high; low++) {
			scope.pagenums.push(low);
		}
	
		scope.onPageChange();
    }

    scope.$watch('currentPage+pageCount+totalCount', function() {
        build();
    	});
       },
    replace: true,
    restrict: "E",
    scope: {
        pageCount: '=',
        currentPage: '=',
        totalCount:'=',
        onPageChange: '&'
    },
    template:
    		'<nav class="paging pull-right">'
    		+'<div class="numbertotal">共&nbsp;{{totalCount}}&nbsp;件</div>'
			+'<ul class="pagination lcnpage">'
			+'<li ng-click="pageChange(1)" class=""><a href="javascript:void(0);"><span aria-hidden="true">{{firstText}}</span></a></li>'                  
			+'<li ng-click="pageChange(currentPage-1>0?currentPage-1:1)" ><a href="javascript:void(0);" aria-label="Previous"><span>{{prevText}}</span></a></li>'
			+'<li><a class="nocolor">{{currentPage<1?1:currentPage}}/{{pageCount<1?1:pageCount}}</a></li>'
			+'<li ng-click="pageChange(currentPage+1<=pageCount?currentPage+1:pageCount)"><a href="javascript:void(0);" aria-label="Next"><span aria-hidden="true">{{nextText}}</span></a></li>'		
			+'<li ng-click="pageChange(pageCount)"><a href="javascript:void(0);"><span aria-hidden="true">{{lastText}}</span></a></li>'
			+'</ul></nav>'
    }
});
