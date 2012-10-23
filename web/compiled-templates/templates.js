(function(){var a=Handlebars.template,b=Handlebars.templates=Handlebars.templates||{};b["base_paginated_list.hbs"]=a(function(a,b,c,d,e){function l(a,b){var d="",e,f;d+='\n<ul class="pager">\n	<li\n		class="previous ',e=a.index_previous_disabled,e=c["if"].call(a,e,{hash:{},inverse:h.noop,fn:h.program(2,m,b)});if(e||e===0)d+=e;d+='"><a\n		href="javascript:paginator.updateList(',f=c.index_previous,f?e=f.call(a,{hash:{}}):(e=a.index_previous,e=typeof e===i?e():e),d+=j(e)+')">&larr; Previous</a></li>\n	<li>',f=c.index_start,f?e=f.call(a,{hash:{}}):(e=a.index_start,e=typeof e===i?e():e),d+=j(e)+"-",f=c.index_end,f?e=f.call(a,{hash:{}}):(e=a.index_end,e=typeof e===i?e():e),d+=j(e)+" of ",f=c.count,f?e=f.call(a,{hash:{}}):(e=a.count,e=typeof e===i?e():e),d+=j(e)+'</li>\n	<li class="next ',e=a.index_next_disabled,e=c["if"].call(a,e,{hash:{},inverse:h.noop,fn:h.program(4,n,b)});if(e||e===0)d+=e;d+='"><a\n		href="javascript:paginator.updateList(',f=c.index_next,f?e=f.call(a,{hash:{}}):(e=a.index_next,e=typeof e===i?e():e),d+=j(e)+')">Next &rarr;</a></li>\n</ul>\n',f=c.block,e=f?f.call(a,"item_list",{hash:{},inverse:h.noop,fn:h.program(6,o,b)}):k.call(a,"block","item_list",{hash:{},inverse:h.noop,fn:h.program(6,o,b)});if(e||e===0)d+=e;d+='\n<ul class="pager">\n	<li\n		class="previous ',e=a.index_previous_disabled,e=c["if"].call(a,e,{hash:{},inverse:h.noop,fn:h.program(8,p,b)});if(e||e===0)d+=e;d+='"><a\n		href="javascript:paginator.updateList(',f=c.index_previous,f?e=f.call(a,{hash:{}}):(e=a.index_previous,e=typeof e===i?e():e),d+=j(e)+')">&larr; Previous</a></li>\n	<li>',f=c.index_start,f?e=f.call(a,{hash:{}}):(e=a.index_start,e=typeof e===i?e():e),d+=j(e)+"-",f=c.index_end,f?e=f.call(a,{hash:{}}):(e=a.index_end,e=typeof e===i?e():e),d+=j(e)+" of ",f=c.count,f?e=f.call(a,{hash:{}}):(e=a.count,e=typeof e===i?e():e),d+=j(e)+'</li>\n	<li class="next ',e=a.index_next_disabled,e=c["if"].call(a,e,{hash:{},inverse:h.noop,fn:h.program(10,q,b)});if(e||e===0)d+=e;return d+='"><a\n		href="javascript:paginator.updateList(',f=c.index_next,f?e=f.call(a,{hash:{}}):(e=a.index_next,e=typeof e===i?e():e),d+=j(e)+')">Next &rarr;</a></li>\n</ul>\n',d}function m(a,b){return"disabled"}function n(a,b){return"disabled"}function o(a,b){return"\n"}function p(a,b){return"disabled"}function q(a,b){return"disabled"}function r(a,b){var d="",e,f;d+='\n<div class="alert alert-warning">\n	<b>Empty!</b> ',f=c.block,e=f?f.call(a,"empty_message",{hash:{},inverse:h.noop,fn:h.program(13,s,b)}):k.call(a,"block","empty_message",{hash:{},inverse:h.noop,fn:h.program(13,s,b)});if(e||e===0)d+=e;return d+=".\n</div>\n",d}function s(a,b){var c="";return c}c=c||a.helpers;var f="",g,h=this,i="function",j=this.escapeExpression,k=c.helperMissing;g=b.items,g=c["if"].call(b,g,{hash:{},inverse:h.noop,fn:h.program(1,l,e)});if(g||g===0)f+=g;f+="\n",g=b.items,g=c.unless.call(b,g,{hash:{},inverse:h.noop,fn:h.program(12,r,e)});if(g||g===0)f+=g;return f+="\n",f}),b["entity_list.hbs"]=a(function(a,b,c,d,e){function n(a,b){var d="",e,f;d+='\n<table class="table table-hover table-striped">\n	<thead>\n		<tr>\n			<th>Name</th>\n			<th style="width: 10%">Action</th>\n		</tr>\n	</thead>\n	<tbody>\n		',f=c.items,f?e=f.call(a,{hash:{},inverse:k.noop,fn:k.programWithDepth(o,b,a)}):(e=a.items,e=typeof e===i?e():e),c.items||(e=m.call(a,e,{hash:{},inverse:k.noop,fn:k.programWithDepth(o,b,a)}));if(e||e===0)d+=e;return d+="\n	</tbody>\n</table>\n",d}function o(a,b,d){var e="",f,g;e+="\n		<tr>\n			<td>",g=c.name,g?f=g.call(a,{hash:{}}):(f=a.name,f=typeof f===i?f():f),e+=j(f)+'</td>\n			<td><a class="btn btn-small btn-link"\n				href="',f=d.mustacheletPath,f=typeof f===i?f():f,e+=j(f)+"/browse/entity/",f=a.type,f=f==null||f===!1?f:f.name,g=c.encodeId,f=g?g.call(a,f,{hash:{},inverse:k.noop,fn:k.program(3,p,b)}):l.call(a,"encodeId",f,{hash:{},inverse:k.noop,fn:k.program(3,p,b)});if(f||f===0)e+=f;e+="/",f=a.name,g=c.encodeId,f=g?g.call(a,f,{hash:{},inverse:k.noop,fn:k.program(5,q,b)}):l.call(a,"encodeId",f,{hash:{},inverse:k.noop,fn:k.program(5,q,b)});if(f||f===0)e+=f;return e+='"><i class=" icon-th-list"></i></a></td>\n		</tr>\n		',e}function p(a,b){var c="";return c}function q(a,b){var c="";return c}function r(a,b){return"There are no defined entities."}c=c||a.helpers,d=d||a.partials;var f="",g,h,i="function",j=this.escapeExpression,k=this,l=c.helperMissing,m=c.blockHelperMissing;h=c.partial,g=h?h.call(b,"item_list",{hash:{},inverse:k.noop,fn:k.program(1,n,e)}):l.call(b,"partial","item_list",{hash:{},inverse:k.noop,fn:k.program(1,n,e)});if(g||g===0)f+=g;f+="\n",h=c.partial,g=h?h.call(b,"empty_message",{hash:{},inverse:k.noop,fn:k.program(7,r,e)}):l.call(b,"partial","empty_message",{hash:{},inverse:k.noop,fn:k.program(7,r,e)});if(g||g===0)f+=g;f+="\n",g=b,g=k.invokePartial(d.base_paginated_list,"base_paginated_list",g,c,d);if(g||g===0)f+=g;return f}),b["event_list.hbs"]=a(function(a,b,c,d,e){function n(a,b){var d="",e,f;d+='\n<table class="table table-hover table-striped">\n	<thead>\n		<tr>\n			<th>&nbsp;</th>\n			<th>Date</th>\n			<th>Event</th>\n			<th>Details</th>\n		</tr>\n	</thead>\n	<tbody>\n		',f=c.items,f?e=f.call(a,{hash:{},inverse:i.noop,fn:i.program(2,o,b)}):(e=a.items,e=typeof e===j?e():e),c.items||(e=m.call(a,e,{hash:{},inverse:i.noop,fn:i.program(2,o,b)}));if(e||e===0)d+=e;return d+="\n	</tbody>\n</table>\n",d}function o(a,b){var d="",e,f;d+='\n		<tr class="',e=a.successful,e=c["if"].call(a,e,{hash:{},inverse:i.noop,fn:i.program(3,p,b)});if(e||e===0)d+=e;e=a.successful,e=c.unless.call(a,e,{hash:{},inverse:i.noop,fn:i.program(5,q,b)});if(e||e===0)d+=e;return d+='">\n			<td>',f=c.type,f?e=f.call(a,{hash:{}}):(e=a.type,e=typeof e===j?e():e),d+=k(e)+'</td>\n			<td style="white-space: nowrap;">',e=a.timestamp,f=c.dateFormat,e=f?f.call(a,e,{hash:{}}):l.call(a,"dateFormat",e,{hash:{}}),d+=k(e)+"</td>\n			<td>",f=c.message,f?e=f.call(a,{hash:{}}):(e=a.message,e=typeof e===j?e():e),d+=k(e)+"</td>\n			<td>",f=c.reason,f?e=f.call(a,{hash:{}}):(e=a.reason,e=typeof e===j?e():e),d+=k(e)+"</td>\n		</tr>\n		",d}function p(a,b){return"success"}function q(a,b){return"error"}function r(a,b){return"No source adaptor events defined."}c=c||a.helpers,d=d||a.partials;var f="",g,h,i=this,j="function",k=this.escapeExpression,l=c.helperMissing,m=c.blockHelperMissing;h=c.partial,g=h?h.call(b,"item_list",{hash:{},inverse:i.noop,fn:i.program(1,n,e)}):l.call(b,"partial","item_list",{hash:{},inverse:i.noop,fn:i.program(1,n,e)});if(g||g===0)f+=g;f+="\n",h=c.partial,g=h?h.call(b,"empty_message",{hash:{},inverse:i.noop,fn:i.program(7,r,e)}):l.call(b,"partial","empty_message",{hash:{},inverse:i.noop,fn:i.program(7,r,e)});if(g||g===0)f+=g;f+="\n",g=b,g=i.invokePartial(d.base_paginated_list,"base_paginated_list",g,c,d);if(g||g===0)f+=g;return f}),b["paginated_list_base.hbs"]=a(function(a,b,c,d,e){function l(a,b){var d="",e,f;d+='\n<ul class="pager">\n	<li\n		class="previous ',e=a.index_previous_disabled,e=c["if"].call(a,e,{hash:{},inverse:h.noop,fn:h.program(2,m,b)});if(e||e===0)d+=e;d+='"><a\n		href="javascript:paginator.updateList(',f=c.index_previous,f?e=f.call(a,{hash:{}}):(e=a.index_previous,e=typeof e===i?e():e),d+=j(e)+')">&larr; Previous</a></li>\n	<li>',f=c.index_start,f?e=f.call(a,{hash:{}}):(e=a.index_start,e=typeof e===i?e():e),d+=j(e)+"-",f=c.index_end,f?e=f.call(a,{hash:{}}):(e=a.index_end,e=typeof e===i?e():e),d+=j(e)+" of ",f=c.count,f?e=f.call(a,{hash:{}}):(e=a.count,e=typeof e===i?e():e),d+=j(e)+'</li>\n	<li class="next ',e=a.index_next_disabled,e=c["if"].call(a,e,{hash:{},inverse:h.noop,fn:h.program(4,n,b)});if(e||e===0)d+=e;d+='"><a\n		href="javascript:paginator.updateList(',f=c.index_next,f?e=f.call(a,{hash:{}}):(e=a.index_next,e=typeof e===i?e():e),d+=j(e)+')">Next &rarr;</a></li>\n</ul>\n',f=c.block,e=f?f.call(a,"item_list",{hash:{},inverse:h.noop,fn:h.program(6,o,b)}):k.call(a,"block","item_list",{hash:{},inverse:h.noop,fn:h.program(6,o,b)});if(e||e===0)d+=e;d+='\n<ul class="pager">\n	<li\n		class="previous ',e=a.index_previous_disabled,e=c["if"].call(a,e,{hash:{},inverse:h.noop,fn:h.program(8,p,b)});if(e||e===0)d+=e;d+='"><a\n		href="javascript:paginator.updateList(',f=c.index_previous,f?e=f.call(a,{hash:{}}):(e=a.index_previous,e=typeof e===i?e():e),d+=j(e)+')">&larr; Previous</a></li>\n	<li>',f=c.index_start,f?e=f.call(a,{hash:{}}):(e=a.index_start,e=typeof e===i?e():e),d+=j(e)+"-",f=c.index_end,f?e=f.call(a,{hash:{}}):(e=a.index_end,e=typeof e===i?e():e),d+=j(e)+" of ",f=c.count,f?e=f.call(a,{hash:{}}):(e=a.count,e=typeof e===i?e():e),d+=j(e)+'</li>\n	<li class="next ',e=a.index_next_disabled,e=c["if"].call(a,e,{hash:{},inverse:h.noop,fn:h.program(10,q,b)});if(e||e===0)d+=e;return d+='"><a\n		href="javascript:paginator.updateList(',f=c.index_next,f?e=f.call(a,{hash:{}}):(e=a.index_next,e=typeof e===i?e():e),d+=j(e)+')">Next &rarr;</a></li>\n</ul>\n',d}function m(a,b){return"disabled"}function n(a,b){return"disabled"}function o(a,b){return"\n"}function p(a,b){return"disabled"}function q(a,b){return"disabled"}function r(a,b){var d="",e,f;d+='\n<div class="alert alert-warning">\n	<b>Empty!</b> ',f=c.block,e=f?f.call(a,"empty_message",{hash:{},inverse:h.noop,fn:h.program(13,s,b)}):k.call(a,"block","empty_message",{hash:{},inverse:h.noop,fn:h.program(13,s,b)});if(e||e===0)d+=e;return d+=".\n</div>\n",d}function s(a,b){var c="";return c}c=c||a.helpers;var f="",g,h=this,i="function",j=this.escapeExpression,k=c.helperMissing;g=b.items,g=c["if"].call(b,g,{hash:{},inverse:h.noop,fn:h.program(1,l,e)});if(g||g===0)f+=g;f+="\n",g=b.items,g=c.unless.call(b,g,{hash:{},inverse:h.noop,fn:h.program(12,r,e)});if(g||g===0)f+=g;return f+="\n",f}),b["plugin_configuration.hbs"]=a(function(a,b,c,d,e){function l(a,b){var d="",e,f;d+="\n<label>",f=c.description,f?e=f.call(a,{hash:{}}):(e=a.description,e=typeof e===h?e():e),d+=i(e),f=c.required,f?e=f.call(a,{hash:{},inverse:j.noop,fn:j.program(2,m,b)}):(e=a.required,e=typeof e===h?e():e),c.required||(e=k.call(a,e,{hash:{},inverse:j.noop,fn:j.program(2,m,b)}));if(e||e===0)d+=e;f=c.hidden,f?e=f.call(a,{hash:{},inverse:j.noop,fn:j.program(4,n,b)}):(e=a.hidden,e=typeof e===h?e():e),c.hidden||(e=k.call(a,e,{hash:{},inverse:j.noop,fn:j.program(4,n,b)}));if(e||e===0)d+=e;return d+='</label>\n<input type="text" value="',f=c.value,f?e=f.call(a,{hash:{}}):(e=a.value,e=typeof e===h?e():e),d+=i(e)+'" name="config.',f=c.key,f?e=f.call(a,{hash:{}}):(e=a.key,e=typeof e===h?e():e),d+=i(e)+'">\n',d}function m(a,b){return" (required)"}function n(a,b){return" (hidden)"}c=c||a.helpers;var f,g,h="function",i=this.escapeExpression,j=this,k=c.blockHelperMissing;return g=c.parameters,g?f=g.call(b,{hash:{},inverse:j.noop,fn:j.program(1,l,e)}):(f=b.parameters,f=typeof f===h?f():f),c.parameters||(f=k.call(b,f,{hash:{},inverse:j.noop,fn:j.program(1,l,e)})),f||f===0?f:""}),b["type_list.hbs"]=a(function(a,b,c,d,e){function n(a,b){var d="",e,f;d+='\n<table class="table table-hover table-striped">\n	<thead>\n		<tr>\n			<th>Name</th>\n			<th>Description</th>\n			<th style="width: 10%">Action</th>\n		</tr>\n	</thead>\n	<tbody>\n		',f=c.items,f?e=f.call(a,{hash:{},inverse:k.noop,fn:k.programWithDepth(o,b,a)}):(e=a.items,e=typeof e===i?e():e),c.items||(e=m.call(a,e,{hash:{},inverse:k.noop,fn:k.programWithDepth(o,b,a)}));if(e||e===0)d+=e;return d+="\n	</tbody>\n</table>\n",d}function o(a,b,d){var e="",f,g;e+="\n		<tr>\n			<td>",g=c.name,g?f=g.call(a,{hash:{}}):(f=a.name,f=typeof f===i?f():f),e+=j(f)+"</td>\n			<td>",g=c.description,g?f=g.call(a,{hash:{}}):(f=a.description,f=typeof f===i?f():f),e+=j(f)+'</td>\n			<td><a class="btn btn-small btn-link"\n				href="',f=d.mustacheletPath,f=typeof f===i?f():f,e+=j(f)+"/browse/type/",f=a.name,g=c.encodeId,f=g?g.call(a,f,{hash:{},inverse:k.noop,fn:k.program(3,p,b)}):l.call(a,"encodeId",f,{hash:{},inverse:k.noop,fn:k.program(3,p,b)});if(f||f===0)e+=f;return e+='"><i class=" icon-th-list"></i></a></td>\n		</tr>\n		',e}function p(a,b){var c="";return c}function q(a,b){return"There are no defined categories."}c=c||a.helpers,d=d||a.partials;var f="",g,h,i="function",j=this.escapeExpression,k=this,l=c.helperMissing,m=c.blockHelperMissing;h=c.partial,g=h?h.call(b,"item_list",{hash:{},inverse:k.noop,fn:k.program(1,n,e)}):l.call(b,"partial","item_list",{hash:{},inverse:k.noop,fn:k.program(1,n,e)});if(g||g===0)f+=g;f+=" ",h=c.partial,g=h?h.call(b,"empty_message",{hash:{},inverse:k.noop,fn:k.program(5,q,e)}):l.call(b,"partial","empty_message",{hash:{},inverse:k.noop,fn:k.program(5,q,e)});if(g||g===0)f+=g;f+="\n",g=b,g=k.invokePartial(d.base_paginated_list,"base_paginated_list",g,c,d);if(g||g===0)f+=g;return f+="\n",f}),b["version_measurement_list.hbs"]=a(function(a,b,c,d,e){function m(a,b){var d="",e;d+='\n<table class="table table-hover table-striped">\n	<thead>\n		<tr>\n			<th>Date</th>\n			<th>Source</th>\n			<th>Adaptor</th>\n		</tr>\n	</thead>\n	<tbody>\n		',e=a.items,e=c.each.call(a,e,{hash:{},inverse:k.noop,fn:k.programWithDepth(n,b,a)});if(e||e===0)d+=e;return d+="\n	</tbody>\n</table>\n",d}function n(a,b,d){var e="",f,g;e+='\n		<tr>\n			<td style="white-space: nowrap;">',f=a.timestamp,g=c.dateFormat,f=g?g.call(a,f,{hash:{}}):l.call(a,"dateFormat",f,{hash:{}}),e+=j(f)+"</td>\n			<td>",f=a.adaptor,f=f==null||f===!1?f:f.source,f=c["with"].call(a,f,{hash:{},inverse:k.noop,fn:k.programWithDepth(o,b,d)});if(f||f===0)e+=f;e+="</td>\n			<td>",f=a.adaptor,f=c["with"].call(a,f,{hash:{},inverse:k.noop,fn:k.programWithDepth(r,b,d)});if(f||f===0)e+=f;return e+="</td>\n		</tr>\n		",e}function o(a,b,d){var e="",f,g;e+='<a href="',f=d.mustacheletPath,f=typeof f===i?f():f,e+=j(f)+"/browse/source/",f=a.name,g=c.encodeId,f=g?g.call(a,f,{hash:{},inverse:k.noop,fn:k.program(4,p,b)}):l.call(a,"encodeId",f,{hash:{},inverse:k.noop,fn:k.program(4,p,b)});if(f||f===0)e+=f;e+='">',g=c.name,g?f=g.call(a,{hash:{}}):(f=a.name,f=typeof f===i?f():f),e+=j(f),f=a.description,f=c["if"].call(a,f,{hash:{},inverse:k.noop,fn:k.program(6,q,b)});if(f||f===0)e+=f;return e+="</a>",e}function p(a,b){var c="";return c}function q(a,b){var d="",e,f;return d+=": ",f=c.description,f?e=f.call(a,{hash:{}}):(e=a.description,e=typeof e===i?e():e),d+=j(e),d}function r(a,b,d){var e="",f,g;e+='<a href="',f=d.mustacheletPath,f=typeof f===i?f():f,e+=j(f)+"/browse/adaptor/",f=a.instance,g=c.encodeId,f=g?g.call(a,f,{hash:{},inverse:k.noop,fn:k.program(9,s,b)}):l.call(a,"encodeId",f,{hash:{},inverse:k.noop,fn:k.program(9,s,b)});if(f||f===0)e+=f;return e+='">',g=c.instance,g?f=g.call(a,{hash:{}}):(f=a.instance,f=typeof f===i?f():f),e+=j(f)+" (",g=c.name,g?f=g.call(a,{hash:{}}):(f=a.name,f=typeof f===i?f():f),e+=j(f)+"-v",g=c.version,g?f=g.call(a,{hash:{}}):(f=a.version,f=typeof f===i?f():f),e+=j(f)+")</a>",e}function s(a,b){var c="";return c}function t(a,b){return"No measurements found."}c=c||a.helpers,d=d||a.partials;var f="",g,h,i="function",j=this.escapeExpression,k=this,l=c.helperMissing;h=c.partial,g=h?h.call(b,"item_list",{hash:{},inverse:k.noop,fn:k.program(1,m,e)}):l.call(b,"partial","item_list",{hash:{},inverse:k.noop,fn:k.program(1,m,e)});if(g||g===0)f+=g;f+="\n",h=c.partial,g=h?h.call(b,"empty_message",{hash:{},inverse:k.noop,fn:k.program(11,t,e)}):l.call(b,"partial","empty_message",{hash:{},inverse:k.noop,fn:k.program(11,t,e)});if(g||g===0)f+=g;f+="\n",g=b,g=k.invokePartial(d.base_paginated_list,"base_paginated_list",g,c,d);if(g||g===0)f+=g;return f})})()